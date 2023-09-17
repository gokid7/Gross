package com.migros.courierService.client;

import com.migros.courierService.dto.RegisterModelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "registerService", path="/v1/register")
public interface RegisterServiceClient {

    @GetMapping(value = "/findByCourierId/{courierId}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RegisterModelDto>> findByCourierId(@PathVariable Long courierId);

    @PostMapping(value = "/registerCourier",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void>registerCourier(@RequestBody RegisterModelDto modelDto);
}
