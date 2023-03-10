package com.app.service;

import java.util.List;

import com.app.dto.PropertyDto;
import com.app.pojos.Owner;
import com.app.pojos.Property;

public interface Property_Service {
	List<Property> getAllDetails();

	Owner addPropertyDetails(PropertyDto transientOwn);

	String deletePropertyDetails(Long PropertyId);

	Property fetchPropertyDetails(Long PropertyId);

	Property updatePropertyDetails(Property detachedProperty);

}