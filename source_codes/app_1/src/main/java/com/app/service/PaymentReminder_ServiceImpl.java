package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.pojos.PaymentReminder;
import com.app.repository.PaymentReminderRepo;

@Service
@Transactional
public class PaymentReminder_ServiceImpl implements PaymentReminder_Service {

	@Autowired
	private PaymentReminderRepo paymentReminderRepo;

	@Override
	public List<PaymentReminder> getAllDetails() {
		return paymentReminderRepo.findAll();
	}

	@Override
	public PaymentReminder addPaymentReminderDetails(PaymentReminder transientPaymentReminder) {
		return paymentReminderRepo.save(transientPaymentReminder);
	}

	@Override
	public String deletePaymentReminderDetails(Long paymentReminderId) {
		if (paymentReminderRepo.existsById(paymentReminderId)) {
			paymentReminderRepo.deleteById(paymentReminderId);
			return "PaymentReminder details deleted ....";
		}
		return "Deletion Failed : Invalid PaymentReminder Id !!!!!!!!!!!";
	}

	@Override
	public PaymentReminder fetchPaymentReminderDetails(Long paymentReminderId) {
		return paymentReminderRepo.findById(paymentReminderId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid PaymentReminder ID !!!!!"));
	}

	@Override
	public PaymentReminder updatePaymentReminderDetails(PaymentReminder detachedPaymentReminder) {
		if (paymentReminderRepo.existsById(detachedPaymentReminder.getId())) {
			return paymentReminderRepo.save(detachedPaymentReminder);
		}
		throw new ResourceNotFoundException("Invalid PaymentReminder Id : Updation Failed!!!!!!!!");
	}

}
