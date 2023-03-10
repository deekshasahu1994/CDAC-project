package com.app.dto;

import java.time.LocalDate;

import com.app.pojos.Client;
import com.app.pojos.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PayReminderDto {
	private String message;
	private LocalDate date;
	private Client client;
	private Payment payment;
}
