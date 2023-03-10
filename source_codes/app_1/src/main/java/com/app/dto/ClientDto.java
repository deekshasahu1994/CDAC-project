package com.app.dto;

import java.time.LocalDate;

import com.app.pojos.Address;

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
public class ClientDto {

	private String firstName;
	private String lastName;
	private Address address;
	private String contactNo;
	private String email;
	private LocalDate enrollDate;
	private Double amount;
	private Long propertyId;
}
