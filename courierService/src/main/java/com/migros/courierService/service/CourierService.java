package com.migros.courierService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.courierService.client.RegisterServiceClient;
import com.migros.courierService.client.StoreServiceClient;
import com.migros.courierService.dto.CourierDto;
import com.migros.courierService.dto.RegisterModelDto;
import com.migros.courierService.dto.StoreDto;
import com.migros.courierService.enums.CourierStatus;
import com.migros.courierService.model.CourierModel;
import com.migros.courierService.model.redis.RedisCourier;
import com.migros.courierService.model.redis.RedisStore;
import com.migros.courierService.model.request.CourierTrackingRequest;
import com.migros.courierService.repository.CourierRepository;
import com.migros.courierService.util.DistanceUtil;
import com.migros.courierService.util.ObserverUtil;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CourierService {

    private final CourierRepository courierRepository;
    private final StoreServiceClient storeServiceClient;
    private final RegisterServiceClient registerServiceClient;
    private final RedisStoreService redisStoreService;
    private final RedisCourierService redisCourierService;
    private final ObjectMapper objectMapper;
    private final DistanceUtil distanceUtil;

    public CourierService(CourierRepository courierRepository, StoreServiceClient storeServiceClient, RegisterServiceClient registerServiceClient, RedisStoreService redisStoreService, RedisCourierService redisCourierService, ObjectMapper objectMapper, DistanceUtil distanceUtil) {
        this.courierRepository = courierRepository;
        this.storeServiceClient = storeServiceClient;
        this.registerServiceClient = registerServiceClient;
        this.redisStoreService = redisStoreService;
        this.redisCourierService = redisCourierService;
        this.objectMapper = objectMapper;
        this.distanceUtil = distanceUtil;
    }

    public CourierDto courierTracking(CourierTrackingRequest request){
        List<StoreDto> stores = storeServiceClient.getAllStores().getBody();
        stores.forEach(store ->{
            RedisStore redisStore = objectMapper.convertValue(store,RedisStore.class);
            redisStoreService.saveStores(redisStore);
        });

        // Kuryeyi, kurye tablosuna ekle.
        CourierModel courierModel = new CourierModel(request.getCourierId());
        courierRepository.save(courierModel);
        String courierStatus = CourierStatus.ON_ROAD.toString();
        String currentStore = "";

        ObserverUtil observerUtil = new ObserverUtil();
        Optional<RedisCourier> existingCourier = redisCourierService.findByCourierId(request.getCourierId());
        if(existingCourier.isPresent()){
            StoreDto existingStore = new StoreDto();
            if("IN_STORE".equals(existingCourier.get().getCourierStatus())){
                existingStore.setName(existingCourier.get().getStoreName());
            }
            RedisCourier tempCourier = existingCourier.get();
            observerUtil.subscribe(existingStore,objectMapper.convertValue(tempCourier,CourierDto.class));
        }

        //stores.parallelStream().

        for (int i=0; i< stores.size(); i++){
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Double hundredCheck = distanceUtil.distance(request.getCourierLat(),stores.get(i).getLat(),request.getCourierLng(),stores.get(i).getLng());
            if(hundredCheck<=100){
                RegisterModelDto registerModel = objectMapper.convertValue(registerServiceClient.findByCourierId(request.getCourierId()).getBody(),RegisterModelDto.class);
                if(registerModel != null){
                    if(registerModel.getTimestamp().getTime() - now.getTime() < 60000){
                        registerServiceClient.registerCourier(prepareRegisterBuilder(now,stores.get(i), registerModel.getCourierId()));
                        courierStatus = CourierStatus.IN_STORE.toString();
                        currentStore = stores.get(i).getName();
                        observerUtil.notifyStore(objectMapper.convertValue(existingCourier,CourierDto.class));
                    }
                }
                else{
                    registerServiceClient.registerCourier(prepareRegisterBuilder(now,stores.get(i), request.getCourierId()));
                    courierStatus = CourierStatus.IN_STORE.toString();
                    currentStore = stores.get(i).getName();
                }

            }
        }
        redisCourierService.saveCourier(prepareRedisCourier(request.getCourierId(), request.getCourierLat(),
                request.getCourierLng(),currentStore,courierStatus));
        return null;
    }

    private RegisterModelDto prepareRegisterBuilder(Timestamp now,StoreDto store,Long courierId){
        RegisterModelDto enteredCourier = RegisterModelDto.builder()
                .timestamp(now)
                .storeId(store.getId())
                .storeName(store.getName())
                .storeLat(store.getLat())
                .storeLng(store.getLng())
                .courierId(courierId).build();
        return enteredCourier;
    }

    private RedisCourier prepareRedisCourier(long courierId,Double lat, Double lng,String storeName,String status){
        RedisCourier redisCourier = RedisCourier.builder()
                        .id(courierId).courierLat(lat).courierLng(lng)
                        .courierStatus(status).storeName(storeName).build();
        return redisCourier;
    }

    public List<RedisStore> getRedisStore(){
        return redisStoreService.getAllStores();
    }


}
