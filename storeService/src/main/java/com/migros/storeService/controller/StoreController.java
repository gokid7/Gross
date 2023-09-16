package com.migros.storeService.controller;

import com.migros.storeService.aspect.ExecutionTime;
import com.migros.storeService.model.StoreModel;
import com.migros.storeService.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/v1/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/getAllStores")
    @ExecutionTime
    public ResponseEntity<List<StoreModel>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }
}
