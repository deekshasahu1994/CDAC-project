package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.LoginRequestDto;
import com.app.dto.SignUpRequestDto;
import com.app.pojos.Owner;
import com.app.repository.OwnerRepo;

@Service
@Transactional
public class Owner_ServiceImpl implements Owner_Service {
	@Autowired
	private OwnerRepo ownerRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Owner> getAllDetails() {
		return ownerRepo.findAll();
	}

	@Override
	public Owner addOwnerDetails(SignUpRequestDto transientOwner) {
		Owner owner = mapper.map(transientOwner, Owner.class);
		owner.setEnabled(false);
		return ownerRepo.save(owner);
	}

	@Override
	public String deleteOwnerDetails(Long ownerId) {
		if (ownerRepo.existsById(ownerId)) {
			ownerRepo.deleteById(ownerId);
			return "owner details deleted ....";
		}
		return "Deletion Failed : Invalid owner Id !!!!!!!!!!!";
	}

	@Override
	public Owner fetchOwnerDetails(Long ownerId) {
		Owner owner = ownerRepo.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid owner ID !!!!!"));
		if (owner.isEnabled()) {
			return owner;
		}
		throw new ResourceNotFoundException("Invalid owner Id : Updation Failed!!!!!!!!");
	}

	@Override
	public Owner updateOwnerDetails(Owner detachedOwner) {
		if (ownerRepo.existsById(detachedOwner.getId())) {
			return ownerRepo.save(detachedOwner);
		}
		throw new ResourceNotFoundException("Invalid owner Id : Updation Failed!!!!!!!!");
	}

	@Override
	public Owner authenticate(LoginRequestDto dto) {
		return ownerRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("Bad Credentials !!!!!"));
	}

	@Override
	public Owner fetchByEmail(String email) {
		// TODO Auto-generated method stub
		return ownerRepo.findByEmailIgnoreCase(email);
	}
}
