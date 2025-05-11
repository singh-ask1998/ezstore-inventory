package com.ezpz.ecom.ezstore.inventory.common.entities;


import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Type;

import com.ezpz.ecom.ezstore.inventory.common.convertor.AddressDetailConvertor;
import com.ezpz.ecom.ezstore.inventory.common.model.AddressDetails;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "product_warehouse")
public class ProductWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long warehouseId;
    
    @Column(name = "registeation_id",unique = true)
    private String registrationId;

    @Column(name = "warehouse_name")
    private String warehouseName;
    
    @Convert(converter = AddressDetailConvertor.class)
    private AddressDetails addressDetails;
    
    //(productId-> (stock,quantity))
    @Type(JsonBinaryType.class)
    @Column(name="product_stock",columnDefinition = "jsonb")
    private Map<String,Map<String,Long>>  productStock;
    
    @ManyToMany(mappedBy = "productWarehouse",fetch = FetchType.LAZY)
    private List<InventoryEntity> inventoryList;
    

}