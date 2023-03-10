package com.app.dto;

import com.app.pojos.Address;

public class PropertyDto {

	private String name;

	private Address address;
	
	private Long ownerId;

	public PropertyDto() {
		// TODO Auto-generated constructor stub
	}

	public PropertyDto(String name, Address address, Long ownerId) {
		super();
		this.name = name;
		this.address = address;
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "PropertyDto [name=" + name + ", address=" + address + ", ownerId=" + ownerId + "]";
	}

	
}
