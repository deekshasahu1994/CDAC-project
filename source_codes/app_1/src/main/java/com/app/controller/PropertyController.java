package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.PropertyDto;
import com.app.pojos.Owner;
import com.app.pojos.Property;
import com.app.service.Property_Service;
@CrossOrigin
@RestController
@RequestMapping("/properties")
public class PropertyController {
	@Autowired
	private Property_Service property_Service;

	public PropertyController() {
	}

	@GetMapping
	public List<Property> getAllPropertys() {
		return property_Service.getAllDetails();
	}

	@PostMapping
	public Owner savePropertyDetails(@RequestBody PropertyDto transientProperty) {
		return property_Service.addPropertyDetails(transientProperty);

	}
	

	@DeleteMapping("/{propertyId}")
	public ApiResponse deletePropertyDetails(@PathVariable Long propertyId) {
		return new ApiResponse(property_Service.deletePropertyDetails(propertyId));
	}

	@GetMapping("/{propertyId}")
	public Property getPropertyDetails(@PathVariable Long propertyId) {
		return property_Service.fetchPropertyDetails(propertyId);
	}

	@PutMapping
	public Property updatePropertyDetails(@RequestBody Property detachedProperty) {
		return property_Service.updatePropertyDetails(detachedProperty);
	}

}
