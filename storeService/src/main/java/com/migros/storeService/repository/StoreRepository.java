package com.migros.storeService.repository;

import com.migros.storeService.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreModel,Long> {
}
