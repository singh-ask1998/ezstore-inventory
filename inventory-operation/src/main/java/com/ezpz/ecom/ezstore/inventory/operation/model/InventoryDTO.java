package com.ezpz.ecom.ezstore.inventory.operation.model;


import java.util.Map;

import com.ezpz.ecom.ezstore.inventory.common.model.AddressDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryDTO {

	private Long inventoryId;
	private Long productId;
	private Long serialNumber;
	private Long warehouseId;
	private String warehouseRegistrationId;
	private String warehouseName;
	private AddressDetails addressDetails;
	private Long productCount;
//	private Map<String,Map<String,Long>>  productStock;
}
