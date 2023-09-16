package com.migros.registerService.repository;

import com.migros.registerService.model.RegisterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterModel,Long> {
    RegisterModel findByCourierId(Long courierId);
}
