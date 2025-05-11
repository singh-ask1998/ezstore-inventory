package com.ezpz.ecom.ezstore.inventory.common.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDetails {

	private String plotNo;
	private String line1;
	private String line2;
	private String line3;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private Double latitude;
	private Double longitude;
}
