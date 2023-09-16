package com.migros.registerService.controller;

import com.migros.registerService.aspect.ExecutionTime;
import com.migros.registerService.dto.RegisterModelDto;
import com.migros.registerService.model.RegisterModel;
import com.migros.registerService.service.RegisterService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/v1/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping(value = "/findByCourierId/{courierId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ExecutionTime
    public ResponseEntity<RegisterModel> findByCourierId(@PathVariable Long courierId) {
        return ResponseEntity.ok(registerService.findByCourierId(courierId));
    }

    @PostMapping(value = "/registerCourier",produces = MediaType.APPLICATION_JSON_VALUE)
    @ExecutionTime
    public ResponseEntity<Void>registerCourier(@RequestBody RegisterModelDto modelDto){
        registerService.registerCourier(modelDto);
        return ResponseEntity.status(201).build();
    }
}
