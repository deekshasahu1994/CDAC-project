package com.app.service;

import java.util.List;

import com.app.dto.PaymentDto;
import com.app.pojos.Owner;
import com.app.pojos.Payment;

public interface Payment_Service {
	List<Payment> getAllDetails();

	Owner addPaymentDetails(PaymentDto transientPayment);

	String deletePaymentDetails(Long paymentId);

	Payment fetchPaymentDetails(Long paymentId);

	Payment updatePaymentDetails(Payment detachedPayment);
}