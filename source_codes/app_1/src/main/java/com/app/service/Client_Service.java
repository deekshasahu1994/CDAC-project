package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.ClientDto;
import com.app.pojos.Client;
import com.app.pojos.Owner;

public interface Client_Service {
	List<Client> getAllDetails();

	Owner addClientDetails(ClientDto transientClient);

	String deleteClientDetails(Long clientId);

	Client fetchClientDetails(Long clientId);

	Client updateClientDetails(Client detachedClient);

	ApiResponse uploadImage(Long clientId, MultipartFile imageFile) throws IOException;

	byte[] serveImage(Long clientId) throws IOException;

}
