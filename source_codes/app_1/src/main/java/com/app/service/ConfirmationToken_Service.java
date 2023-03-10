package com.app.service;

import com.app.pojos.ConfirmationToken;

public interface ConfirmationToken_Service {
    ConfirmationToken fetchByConfirmationToken(String confirmationToken);

	void save(ConfirmationToken confirmationToken);
	

}
