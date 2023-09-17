package com.migros.courierService.client;

import com.migros.courierService.dto.StoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "storeService", path="/v1/store")
public interface StoreServiceClient {

    @GetMapping("/getAllStores")
    ResponseEntity<List<StoreDto>> getAllStores();
}
