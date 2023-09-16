package com.migros.courierService.service;

import com.migros.courierService.model.redis.RedisStore;
import com.migros.courierService.repository.RedisStoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RedisStoreService {

    private final RedisStoreRepository storeRepository;

    public RedisStoreService(RedisStoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<RedisStore> getAllStores() {
        return new ArrayList<RedisStore>((Collection<? extends RedisStore>) storeRepository.findAll());
    }

    public RedisStore saveStores(RedisStore store){
        return storeRepository.save(store);
    }

}
