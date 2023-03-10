package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.ClientDto;
import com.app.pojos.Client;
import com.app.pojos.Owner;
import com.app.pojos.Property;
import com.app.repository.ClientRepo;
import com.app.repository.OwnerRepo;
import com.app.repository.PropertyRepo;

@Service
@Transactional
public class Client_SeviceImpl implements Client_Service {
	@Value("${content.upload.folder}")
	private String folderName;

	@Autowired
	private ClientRepo clientRepo;

	@Autowired
	private PropertyRepo propertyRepo;

	@Autowired
	private OwnerRepo ownerRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Client> getAllDetails() {
		return clientRepo.findAll();
	}

	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + folderName);
		// chk of folder exists --o.w create one!
		File path = new File(folderName);
		if (!path.exists()) {
			path.mkdirs();
		} else
			System.out.println("folder alrdy exists....");
	}

	@Override
	public Owner addClientDetails(ClientDto transientClient) {
		Client client = mapper.map(transientClient, Client.class);
		Property property = propertyRepo.findById(transientClient.getPropertyId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Property ID !!!!!"));
		Owner owner = ownerRepo.findById(property.getUser().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Property ID !!!!!"));
		client.setProperty(property);
		property.getClients().add(client);
		owner.getClients().add(client);
		return ownerRepo.save(owner);
	}

	@Override
	public String deleteClientDetails(Long clientId) {
		if (clientRepo.existsById(clientId)) {
			clientRepo.deleteById(clientId);
			return "Client details deleted ....";
		}
		return "Deletion Failed : Invalid Client Id !!!!!!!!!!!";
	}

	@Override
	public Client fetchClientDetails(Long clientId) {
		return clientRepo.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Client ID !!!!!"));
	}

	@Override
	public Client updateClientDetails(Client detachedClient) {
		if (clientRepo.existsById(detachedClient.getId())) {
			return clientRepo.save(detachedClient);
		}
		throw new ResourceNotFoundException("Invalid Client Id : Updation Failed!!!!!!!!");
	}

	@Override
	public ApiResponse uploadImage(Long clientId, MultipartFile imageFile) throws IOException {
		Client client = clientRepo.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid client Id : Image Uploading failed!!!!!!!!"));
		String targetPath = folderName + File.separator + imageFile.getOriginalFilename();

		System.out.println(targetPath);
		Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
		client.setImagePath(targetPath);
		return new ApiResponse("Image Uploaded successfully!");
	}

	@Override
	public byte[] serveImage(Long clientId) throws IOException {
		Client client = clientRepo.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid client Id : Image Download failed!!!!!!!!"));
		String path = client.getImagePath();
		if (path == null)
			throw new ResourceNotFoundException("Image does not exist !!!!!");
		return Files.readAllBytes(Paths.get(path));

	}
}
