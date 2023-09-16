package com.migros.courierService.repository;

import com.migros.courierService.model.redis.RedisCourier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisCourierRepository extends CrudRepository<RedisCourier,Long> {
}
