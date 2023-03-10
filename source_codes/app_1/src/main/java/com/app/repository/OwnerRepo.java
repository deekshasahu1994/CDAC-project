package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Owner;

public interface OwnerRepo extends JpaRepository<Owner, Long>{
	Optional<Owner> findByEmailAndPassword(String email, String password);
	Owner findByEmailIgnoreCase(String email);


}
