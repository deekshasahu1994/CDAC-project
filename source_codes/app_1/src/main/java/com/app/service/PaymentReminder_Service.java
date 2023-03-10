package com.app.service;

import java.util.List;

import com.app.pojos.PaymentReminder;

public interface PaymentReminder_Service {
	List<PaymentReminder> getAllDetails();

	PaymentReminder addPaymentReminderDetails(PaymentReminder transientPaymentReminder);

	String deletePaymentReminderDetails(Long paymentReminderId);

	PaymentReminder fetchPaymentReminderDetails(Long paymentReminderId);

	PaymentReminder updatePaymentReminderDetails(PaymentReminder detachedPaymentReminder);
}
