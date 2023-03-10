package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.PaymentDto;
import com.app.pojos.Client;
import com.app.pojos.Owner;
import com.app.pojos.Payment;
import com.app.pojos.Property;
import com.app.repository.ClientRepo;
import com.app.repository.OwnerRepo;
import com.app.repository.PaymentRepo;
import com.app.repository.PropertyRepo;

@Service
@Transactional
public class Payment_ServiceImpl implements Payment_Service {

	@Autowired
	private PaymentRepo paymentRepo;

	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private OwnerRepo ownerRepo;
	
	@Autowired
	private PropertyRepo propertyRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Payment> getAllDetails() {
		return paymentRepo.findAll();
	}

	@Override
	public Owner addPaymentDetails(PaymentDto transientPayment) {
		Payment payment = mapper.map(transientPayment, Payment.class);
		System.out.println(payment);
		Client client = clientRepo.findById(transientPayment.getClientId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Payment ID !!!!!"));
		Property property = propertyRepo.findById(client.getProperty().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Payment ID !!!!!"));
		Owner owner = ownerRepo.findById(property.getUser().getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Payment ID !!!!!"));
		payment.setClient(client);
		client.getPayments().add(payment);
		property.getClients().add(client);
		owner.getProperties().add(property);
		owner.getClients().add(client);
		return ownerRepo.save(owner);
	}

	@Override
	public String deletePaymentDetails(Long paymentId) {
		if (paymentRepo.existsById(paymentId)) {
			paymentRepo.deleteById(paymentId);
			return "Payment details deleted ....";
		}
		return "Deletion Failed : Invalid Payment Id !!!!!!!!!!!";
	}

	@Override
	public Payment fetchPaymentDetails(Long paymentId) {
		return paymentRepo.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Payment ID !!!!!"));
	}

	@Override
	public Payment updatePaymentDetails(Payment detachedPayment) {
		if (paymentRepo.existsById(detachedPayment.getId())) {
			return paymentRepo.save(detachedPayment);
		}
		throw new ResourceNotFoundException("Invalid Payment Id : Updation Failed!!!!!!!!");
	}

}
