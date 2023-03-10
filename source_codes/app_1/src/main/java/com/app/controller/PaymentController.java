package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.PaymentDto;
import com.app.pojos.Owner;
import com.app.pojos.Payment;
import com.app.service.Payment_Service;
@CrossOrigin
@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	private Payment_Service payment_Service;

	public PaymentController() {
	}

	@GetMapping
	public List<Payment> getAllPayments() {
		return payment_Service.getAllDetails();
	}

	@PostMapping
	public Owner savePaymentDetails(@RequestBody PaymentDto transientPayment) {
		System.out.println(transientPayment);
		return payment_Service.addPaymentDetails(transientPayment);

	}

	@DeleteMapping("/{PaymentId}")
	public ApiResponse deletePaymentDetails(@PathVariable Long PaymentId) {
		return new ApiResponse(payment_Service.deletePaymentDetails(PaymentId));
	}

	@GetMapping("/{PaymentId}")
	public Payment getPaymentDetails(@PathVariable Long PaymentId) {
		return payment_Service.fetchPaymentDetails(PaymentId);
	}

	@PutMapping
	public Payment updatePaymentDetails(@RequestBody Payment detachedPayment) {
		return payment_Service.updatePaymentDetails(detachedPayment);
	}

}
