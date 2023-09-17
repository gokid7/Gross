package com.migros.courierService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.migros.courierService.dto.CourierDto;
import com.migros.courierService.exception.BadRequestException;
import com.migros.courierService.exception.ResourceNotFoundException;
import com.migros.courierService.model.redis.RedisStore;
import com.migros.courierService.model.request.CourierTrackingRequest;
import com.migros.courierService.model.response.CourierBaseResponse;
import com.migros.courierService.service.CourierService;
import com.migros.courierService.service.RedisStoreService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/v1/courier")
public class CourierController {

    private final CourierService courierService;
    private final RedisStoreService redisService;

    public CourierController(CourierService courierService, RedisStoreService redisService) {
        this.courierService = courierService;
        this.redisService = redisService;
    }

    @PostMapping(value = "/courierTracking",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourierBaseResponse> courierTracking(@RequestBody CourierTrackingRequest request) throws JsonProcessingException {
        courierService.courierTracking(request);
        return ResponseEntity.ok(new CourierBaseResponse<>(null,true,null));
    }

    @PostMapping(value = "/getTotalTravelDistance/{courierId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourierBaseResponse<Double>> getTotalTravelDistance(@PathVariable long courierId) {
        Double result = courierService.getTotalTravelDistance(courierId);
        return ResponseEntity.ok(new CourierBaseResponse<>(result,true,null));
    }

    @PostMapping(value = "/getRedisStores",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourierBaseResponse<List<RedisStore>>> getRedisStores(){
        List<RedisStore> result = courierService.getRedisStore();
        return ResponseEntity.ok(new CourierBaseResponse<>(result,true,null));
    }



}
