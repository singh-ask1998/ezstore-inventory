package com.ezpz.ecom.ezstore.inventory.common.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ezpz.ecom.ezstore.inventory.common.entities.InventoryEntity;

@Repository
public interface ProductInventoryRepo extends JpaRepository<InventoryEntity, Long>{

	Optional<InventoryEntity> findByProductIdAndSerialNumber(Long productId, Long serialNumber);
}
