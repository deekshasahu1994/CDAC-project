package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pojos.ConfirmationToken;
import com.app.repository.ConfirmationTokenRepo;

@Service
@Transactional
public class ConfirmationToken_ServiceImpl implements ConfirmationToken_Service {

	@Autowired
	private ConfirmationTokenRepo confirmationTokenRepo;

	@Override
	public ConfirmationToken fetchByConfirmationToken(String confirmationToken) {
		return confirmationTokenRepo.findByConfirmationToken(confirmationToken);
	}

	@Override
	public void save(ConfirmationToken confirmationToken) {
		confirmationTokenRepo.save(confirmationToken);
	}

}
