package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.ClientDto;
import com.app.pojos.Client;
import com.app.pojos.Owner;
import com.app.service.Client_Service;
@CrossOrigin
@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private Client_Service client_Service;

	public ClientController() {
	}

	@GetMapping
	public List<Client> getAllClients() {
		return client_Service.getAllDetails();
	}

	@PostMapping
	public Owner saveClientDetails(@RequestBody ClientDto transientClient) {
		return client_Service.addClientDetails(transientClient);

	}

	@DeleteMapping("/{clientId}")
	public ApiResponse deleteClientDetails(@PathVariable Long clientId) {
		return new ApiResponse(client_Service.deleteClientDetails(clientId));
	}

	@GetMapping("/{clientId}")
	public Client getClientDetails(@PathVariable Long clientId) {
		return client_Service.fetchClientDetails(clientId);
	}

	@PutMapping
	public Client updateClientDetails(@RequestBody Client detachedClient) {
		return client_Service.updateClientDetails(detachedClient);
	}
	
	@PostMapping(value="/{clientId}/image_upload",consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImageToServerSideFolder(@PathVariable Long clientId,
			@RequestParam MultipartFile imageFile) throws IOException{
		System.out.println("in upload img " + clientId + " " + imageFile.getOriginalFilename());
		return new ResponseEntity<>(client_Service.uploadImage(clientId, imageFile), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{clientId}/image", produces = { MediaType.IMAGE_GIF_VALUE, 
			MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> serveImageFromServerSideFolder(@PathVariable Long clientId) throws IOException {
		System.out.println("in serve img " + clientId);
		return new ResponseEntity<>(client_Service.serveImage(clientId), HttpStatus.OK);
	}
	
	

}
