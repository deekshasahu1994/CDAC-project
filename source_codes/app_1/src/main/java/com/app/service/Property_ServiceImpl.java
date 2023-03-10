package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.PropertyDto;
import com.app.pojos.Owner;
import com.app.pojos.Property;
import com.app.repository.OwnerRepo;
import com.app.repository.PropertyRepo;

@Service
@Transactional
public class Property_ServiceImpl implements Property_Service {

	@Autowired
	private PropertyRepo propertyRepo;

	@Autowired
	private OwnerRepo ownerRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Property> getAllDetails() {
		return propertyRepo.findAll();
	}

	
	
	@Override
	public Owner addPropertyDetails(PropertyDto transientProperty) {
		Property property = mapper.map(transientProperty, Property.class);
		Owner owner = ownerRepo.findById(transientProperty.getOwnerId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Property ID !!!!!"));
		property.setUser(owner);
		owner.getProperties().add(property);
		return ownerRepo.save(owner);
	}
	
	@Override
	public String deletePropertyDetails(Long propertyId) {
		if (propertyRepo.existsById(propertyId)) {
			propertyRepo.deleteById(propertyId);
			return "Property details deleted ....";
		}
		return "Deletion Failed : Invalid Property Id !!!!!!!!!!!";
	}

	@Override
	public Property fetchPropertyDetails(Long propertyId) {
		return propertyRepo.findById(propertyId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Property ID !!!!!"));
	}

	@Override
	public Property updatePropertyDetails(Property detachedProperty) {
		if (propertyRepo.existsById(detachedProperty.getId())) {
			return propertyRepo.save(detachedProperty);
		}
		throw new ResourceNotFoundException("Invalid Property Id : Updation Failed!!!!!!!!");
	}


}
