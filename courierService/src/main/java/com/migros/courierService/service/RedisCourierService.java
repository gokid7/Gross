package com.migros.courierService.service;

import com.migros.courierService.model.redis.RedisCourier;
import com.migros.courierService.model.redis.RedisStore;
import com.migros.courierService.repository.RedisCourierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RedisCourierService {

    private final RedisCourierRepository redisCourierRepository;

    public RedisCourierService(RedisCourierRepository redisCourierRepository) {
        this.redisCourierRepository = redisCourierRepository;
    }

    public List<RedisCourier> getAllCourier() {
        return new ArrayList<RedisCourier>((Collection<? extends RedisCourier>) redisCourierRepository.findAll());
    }

    public Optional<RedisCourier> findByCourierId(Long courierId){
        return redisCourierRepository.findById(courierId);
    }

    public RedisCourier saveCourier(RedisCourier courier){
        return redisCourierRepository.save(courier);
    }
}
