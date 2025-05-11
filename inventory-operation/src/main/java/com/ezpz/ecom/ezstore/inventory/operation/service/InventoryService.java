package com.ezpz.ecom.ezstore.inventory.operation.service;

import com.ezpz.ecom.ezstore.inventory.operation.model.InventoryDTO;
import com.ezpz.ecom.ezstore.product.common.util.APIResponse;

public interface InventoryService {

	public InventoryDTO getInventoryData();
	
	public APIResponse saveInventoryData(InventoryDTO inventroyDTO);
	
}
