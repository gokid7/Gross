package com.migros.courierService.repository;

import com.migros.courierService.model.CourierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<CourierModel,Long> {
}
