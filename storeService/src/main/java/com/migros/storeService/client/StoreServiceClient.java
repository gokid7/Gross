package com.migros.storeService.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "storeService", path="/v1/store")
public interface StoreServiceClient {
}
