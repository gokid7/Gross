package com.migros.courierService.repository;

import com.migros.courierService.model.redis.RedisStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisStoreRepository extends CrudRepository<RedisStore,Long> {

}
