package com.ezpz.ecom.ezstore.inventory.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezpz.ecom.ezstore.inventory.operation.model.InventoryDTO;
import com.ezpz.ecom.ezstore.inventory.operation.service.InventoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/inventory")
@Tag(name = "Inventory Management", description = "Operations related to Inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping("/getData")
	public ResponseEntity<?> fetchDataFromInventory(@RequestParam Long productId, @RequestParam Long serialNumber) {
		return null;
	}
	
	@GetMapping("/getDemo")
	public String fetchDemo() {
		return "Demo";
	}
	
	@Operation(summary = "Save Product in warehouse", description = "Save Product in warehouse")
	@PostMapping("/saveData")
	public ResponseEntity<?> addDataInWareHouse(@RequestBody InventoryDTO inventroyDTO){
		return ResponseEntity.status(200).body(inventoryService.saveInventoryData(inventroyDTO));
	}

}
