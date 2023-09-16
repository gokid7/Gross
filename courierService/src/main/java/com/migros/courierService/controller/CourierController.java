package com.migros.courierService.controller;

import com.migros.courierService.dto.CourierDto;
import com.migros.courierService.model.redis.RedisStore;
import com.migros.courierService.model.request.CourierTrackingRequest;
import com.migros.courierService.service.CourierService;
import com.migros.courierService.service.RedisStoreService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CourierDto> courierTracking(@RequestBody CourierTrackingRequest request){
        return ResponseEntity.ok(courierService.courierTracking(request));
    }

    @PostMapping(value = "/getRedisStores",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RedisStore>> getRedisStores(){
        return ResponseEntity.ok(courierService.getRedisStore());
    }



}
