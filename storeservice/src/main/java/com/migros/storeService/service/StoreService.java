package com.migros.storeService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.storeService.dto.StoreModelListDto;
import com.migros.storeService.model.StoreModel;
import com.migros.storeService.repository.StoreRepository;
import com.migros.storeService.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class StoreService {

    private final ObjectMapper objectMapper;
    private final StoreRepository storeRepository;
    private final JsonUtil jsonUtil;

    public StoreService(ObjectMapper objectMapper, StoreRepository storeRepository, JsonUtil jsonUtil) {
        this.objectMapper = objectMapper;
        this.storeRepository = storeRepository;
        this.jsonUtil = jsonUtil;
    }

    public void prepareStores() throws IOException {
        String jsonString = jsonUtil.convertFileToString("stores.json");
        StoreModelListDto list = objectMapper.readValue(jsonString,StoreModelListDto.class);
        list.getStores().forEach(store -> {
            storeRepository.save(store);
            System.out.println("Store saved : " + store.getName());
        });
    }

    public List<StoreModel> getAllStores(){
        return storeRepository.findAll();
    }
}
