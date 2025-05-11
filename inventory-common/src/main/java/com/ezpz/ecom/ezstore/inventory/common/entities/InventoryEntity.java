package com.ezpz.ecom.ezstore.inventory.common.entities;

import java.util.List;
import java.util.Map;

import com.ezpz.ecom.ezstore.inventory.common.model.AddressDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "product_inventory")
public class InventoryEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;
	
	
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "serial_number")
    private Long serialNumber;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_inventory_warehouse",
            joinColumns = @JoinColumn(name = "inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id")
        )
    private List<ProductWarehouse> productWarehouse;
	
}
