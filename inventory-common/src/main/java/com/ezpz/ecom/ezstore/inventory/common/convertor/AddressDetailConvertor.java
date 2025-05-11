package com.ezpz.ecom.ezstore.inventory.common.convertor;



import java.io.IOException;

import com.ezpz.ecom.ezstore.inventory.common.model.AddressDetails;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AddressDetailConvertor implements AttributeConverter<AddressDetails, String> {


	    private final ObjectMapper objectMapper = new ObjectMapper();

	    @Override
	    public String convertToDatabaseColumn(AddressDetails object) {
	        try {
	            if (object == null) {
	                return null;
	            }
	            // Serialize the object to a JSON string
	            return objectMapper.writeValueAsString(object);
	        } catch (IOException e) {
	            throw new IllegalArgumentException("Error converting object to JSON", e);
	        }
	    }

	    @Override
	    public AddressDetails convertToEntityAttribute(String dbData) {
	        try {
	            if (dbData == null) {
	                return null;
	            }
	            // Deserialize the JSON string back to an object (use the appropriate class)
	            return objectMapper.readValue(dbData, AddressDetails.class);
	        } catch (IOException e) {
	            throw new IllegalArgumentException("Error converting JSON to object", e);
	        }
	    }
}
