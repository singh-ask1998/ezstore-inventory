package com.ezpz.ecom.ezstore.inventory.operation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezpz.ecom.ezstore.inventory.common.entities.InventoryEntity;
import com.ezpz.ecom.ezstore.inventory.common.entities.ProductWarehouse;
import com.ezpz.ecom.ezstore.inventory.common.repo.ProductInventoryRepo;
import com.ezpz.ecom.ezstore.inventory.common.repo.ProductWarehouseRepo;
import com.ezpz.ecom.ezstore.inventory.operation.model.InventoryDTO;
import com.ezpz.ecom.ezstore.inventory.operation.service.InventoryService;
import com.ezpz.ecom.ezstore.product.common.entities.ProductInventory;
import com.ezpz.ecom.ezstore.product.common.util.APIResponse;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private ProductInventoryRepo productInventoryRepo;

	@Autowired
	private ProductWarehouseRepo productWarehouseRepo;

	@Override
	public InventoryDTO getInventoryData() {
		InventoryDTO inventroyDTO = null;
		return null;
	}

	@Override
	public APIResponse saveInventoryData(InventoryDTO inventroyDTO) {

		ProductWarehouse productWarehouse = fetchWareHouseEntity(inventroyDTO);
		InventoryEntity inventoryEntity = fetchProductInventory(inventroyDTO);
		if (productWarehouse == null) {
			return APIResponse.builder().status("400")

					.message(
							"Warehouse details is not present in database and not sufficient details to save new data.")
					.build();
		}
		if (inventoryEntity == null) {
			return APIResponse.builder().status("400")
					.message(
							"Inventory details is not present in database and not sufficient details to save new data.")
					.build();
		}
		
		try {
			updateWarehouseEntityProductStock(productWarehouse,inventroyDTO);
			processWareHouseEntity(productWarehouse, inventroyDTO, inventoryEntity);
			processInventoryEntity(inventoryEntity,inventroyDTO,productWarehouse);
			
			productWarehouseRepo.save(productWarehouse);
			productInventoryRepo.save(inventoryEntity);

			return APIResponse.builder().status("201").message("Successfully saved the data.").build();
		}

		catch (Exception e) {

			return APIResponse.builder().status("500").message("Internal issue: " + e.getMessage()).build();
		}
//		return null;
	}

	public ProductWarehouse fetchWareHouseEntity(InventoryDTO inventroyDTO) {
		Optional<ProductWarehouse> productWarehouse = Optional.empty();
		
			
	    productWarehouse=productWarehouseRepo.findByRegistrationId(inventroyDTO.getWarehouseRegistrationId());
			
		
		if (productWarehouse.isPresent()) {
			return productWarehouse.get();
		} else {
			if (inventroyDTO.getAddressDetails() == null || inventroyDTO.getWarehouseName() == null
					) {
				return null;
			} else {
				return ProductWarehouse.builder().addressDetails(inventroyDTO.getAddressDetails())
						.registrationId(inventroyDTO.getWarehouseRegistrationId())
						.warehouseName(inventroyDTO.getWarehouseName()).build();

			}
		}

	}

	public InventoryEntity fetchProductInventory(InventoryDTO inventroyDTO) {
		Optional<InventoryEntity> inventoryOptional = Optional.empty();
		
			inventoryOptional = productInventoryRepo.findByProductIdAndSerialNumber(inventroyDTO.getProductId(),inventroyDTO.getSerialNumber());
		
		
		if (inventoryOptional.isPresent()) {
			return inventoryOptional.get();
		} else {
			if (inventroyDTO.getSerialNumber() == null || inventroyDTO.getProductId() == null) {
				return null;
			} else {
				return InventoryEntity.builder().serialNumber(inventroyDTO.getSerialNumber())
						.productId(inventroyDTO.getProductId()).build();

			}

		}
	}

	public void updateWarehouseEntityProductStock(ProductWarehouse productWarehouse, InventoryDTO inventroyDTO) {
		if (productWarehouse.getProductStock() != null) {
			if (productWarehouse.getProductStock().containsKey(inventroyDTO.getProductId().toString())) {
				if (productWarehouse.getProductStock().get(inventroyDTO.getProductId().toString())
						.containsKey(inventroyDTO.getSerialNumber().toString())) {
					Long count = inventroyDTO.getProductCount() + productWarehouse.getProductStock().get(inventroyDTO.getProductId().toString())
							.get(inventroyDTO.getSerialNumber().toString());
					productWarehouse.getProductStock()
							.get(inventroyDTO.getProductId().toString())
							.put(inventroyDTO.getSerialNumber().toString(), count);
				} else {
					Map<String, Long> innerMap = new HashMap<>();
//				innerMap.put(inventroyDTO.getSerialNumber().toString(), inventroyDTO.getProductCount());
					productWarehouse.getProductStock()
							.get(inventroyDTO.getProductId().toString())
							.put(inventroyDTO.getSerialNumber().toString(), inventroyDTO.getProductCount());
//				productStock.get(inventroyDTO.getProductId().toString()).put(null, null)
				}
			} else {
//			Map<String,Map<String,Long>> outerMap=new HashMap<>();
				Map<String, Long> innerMap = new HashMap<>();
				innerMap.put(inventroyDTO.getSerialNumber().toString(), inventroyDTO.getProductCount());
//			outerMap.put(inventroyDTO.getProductId().toString(), innerMap);
				productWarehouse.getProductStock()
						.put(inventroyDTO.getProductId().toString(), innerMap);

			}
		} else {
			Map<String, Map<String, Long>> outerMap = new HashMap<>();
			Map<String, Long> innerMap = new HashMap<>();
			innerMap.put(inventroyDTO.getSerialNumber().toString(), inventroyDTO.getProductCount());
			outerMap.put(inventroyDTO.getProductId().toString(), innerMap);
			productWarehouse.setProductStock(outerMap);
		}
	}

	public void processWareHouseEntity(ProductWarehouse productWarehouse, InventoryDTO inventroyDTO,
			InventoryEntity inventoryEntity) {
		List<InventoryEntity> ls=productWarehouse.getInventoryList();
		if (ls==null || ls.isEmpty()) {
			List<InventoryEntity> lst = new ArrayList<>();
			lst.add(inventoryEntity);
			productWarehouse.setInventoryList(lst);
		} else {
			productWarehouse.getInventoryList().stream()
					.filter(d -> d.getInventoryId() == inventroyDTO.getInventoryId()).findFirst().orElseGet(() -> {
						productWarehouse.getInventoryList().add(inventoryEntity);
						return inventoryEntity;
					});
		}
	}

	public void processInventoryEntity(InventoryEntity inventoryEntity, InventoryDTO inventroyDTO,
			ProductWarehouse productWarehouse) {

		if (inventoryEntity.getProductWarehouse()==null || inventoryEntity.getProductWarehouse().isEmpty()) {
			List<ProductWarehouse> lst = new ArrayList<>();
			lst.add(productWarehouse);
			inventoryEntity.setProductWarehouse(lst);
		} else {
			OptionalInt opi = IntStream.range(0, inventoryEntity.getProductWarehouse().size()).filter(
					i -> inventroyDTO.getWarehouseId() == inventoryEntity.getProductWarehouse().get(i).getWarehouseId())
					.findFirst();

			if (opi.isPresent()) {
				inventoryEntity.getProductWarehouse().set(opi.getAsInt(), productWarehouse);
			} else {inventoryEntity.getProductWarehouse().add(productWarehouse);
			}
		}
	}

//	public void updateProductCountIfExistInExistingWareHouse(List<ProductWarehouse> warehouseList, InventoryDTO inventroyDTO) {
//		 
////		warehouseList.stream().filter(d-> d.getWarehouseId())
//		
//	}
}
