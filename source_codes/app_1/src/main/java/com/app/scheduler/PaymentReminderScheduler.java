package com.app.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.pojos.Payment;
import com.app.pojos.Status;
import com.app.service.EmailService;
import com.app.service.Payment_Service;

@Component
public class PaymentReminderScheduler {

	@Autowired
	private Payment_Service paymentService;

	@Autowired
	private EmailService emailService;

//	@Scheduled(cron = "0 0 12 * * ?") // run every day at 12 PM
	@Scheduled(fixedDelay = 1000*60)
	public void sendPayments() {
		List<Payment> Payments = paymentService.getAllDetails();

		for (Payment Payment : Payments) {
			if (Payment.getDueDate().isEqual(LocalDate.now())) {
				Payment.getClient().setStatus(Status.PENDING);
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(Payment.getClient().getEmail());
				mailMessage.setSubject("Payment reminder for the due month " + Payment.getDueDate().getMonth());
				mailMessage.setText("Kindly pay your rent on the given mobile number "
						+ Payment.getClient().getProperty().getUser().getContactNo() + ".\nRegards,\n"
						+ Payment.getClient().getProperty().getUser().getFirstName().toUpperCase() + " "
						+ Payment.getClient().getProperty().getUser().getLastName().toUpperCase());
				emailService.sendEmail(mailMessage);

				SimpleMailMessage mailMessageToOwner = new SimpleMailMessage();
				mailMessageToOwner.setTo(Payment.getClient().getProperty().getUser().getEmail());
				mailMessageToOwner.setSubject("Payment Reminder");
				mailMessageToOwner.setText("Payment reminder for the due month " + Payment.getDueDate().getMonth()
						+ " of Mr." + Payment.getClient().getFirstName().toUpperCase() + " "
						+ Payment.getClient().getLastName().toUpperCase() + " has been sent.");
				emailService.sendEmail(mailMessageToOwner);

			}
		}
	}

}