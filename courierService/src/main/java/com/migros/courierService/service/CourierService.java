package com.migros.courierService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.courierService.client.RegisterServiceClient;
import com.migros.courierService.client.StoreServiceClient;
import com.migros.courierService.dto.CourierDto;
import com.migros.courierService.dto.StoreDto;
import com.migros.courierService.model.redis.RedisStore;
import com.migros.courierService.model.request.CourierTrackingRequest;
import com.migros.courierService.repository.CourierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {

    private final CourierRepository courierRepository;
    private final StoreServiceClient storeServiceClient;
    private final RegisterServiceClient registerServiceClient;
    private final RedisStoreService redisStoreService;
    private final ObjectMapper objectMapper;

    public CourierService(CourierRepository courierRepository, StoreServiceClient storeServiceClient, RegisterServiceClient registerServiceClient, RedisStoreService redisStoreService, ObjectMapper objectMapper) {
        this.courierRepository = courierRepository;
        this.storeServiceClient = storeServiceClient;
        this.registerServiceClient = registerServiceClient;
        this.redisStoreService = redisStoreService;
        this.objectMapper = objectMapper;
    }

    public CourierDto courierTracking(CourierTrackingRequest request){
        List<StoreDto> stores = storeServiceClient.getAllStores().getBody();
        stores.forEach(store ->{
            RedisStore redisStore = objectMapper.convertValue(store,RedisStore.class);
            redisStoreService.saveStores(redisStore);
        });

        return null;
    }

    public List<RedisStore> getRedisStore(){
        return redisStoreService.getAllStores();
    }


}
