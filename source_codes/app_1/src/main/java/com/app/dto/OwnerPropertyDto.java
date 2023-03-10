package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import com.app.pojos.Property;

public class OwnerPropertyDto{

	private Long id;
	private Set<Property> properties = new HashSet<>();

	public OwnerPropertyDto() {
		// TODO Auto-generated constructor stub
	}

	public OwnerPropertyDto(Long id, Set<Property> properties) {
		super();
		this.id = id;
		this.properties = properties;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	

}
