package com.app.service;

import java.util.List;

import com.app.dto.LoginRequestDto;
import com.app.dto.SignUpRequestDto;
import com.app.pojos.Owner;

public interface Owner_Service {
	List<Owner> getAllDetails();

	Owner addOwnerDetails(SignUpRequestDto transientOwner);

	String deleteOwnerDetails(Long ownerId);

	Owner fetchOwnerDetails(Long ownerId);

	Owner updateOwnerDetails(Owner detachedOwner);

	Owner authenticate(LoginRequestDto dto);
	
	Owner fetchByEmail(String email);
	
	
}
