package com.ezpz.ecom.ezstore.inventory.common.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezpz.ecom.ezstore.inventory.common.entities.ProductWarehouse;


@Repository
public interface ProductWarehouseRepo extends JpaRepository<ProductWarehouse, Long>{

	Optional<ProductWarehouse> findByRegistrationId(String registrationId);
	
}
