package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Property;

public interface PropertyRepo extends JpaRepository<Property, Long>{
	
}
