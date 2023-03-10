package com.app.dto;

import com.app.pojos.Role;

public class SignUpRequestDto {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String contactNo;
	private Role role;

	public SignUpRequestDto() {
		// TODO Auto-generated constructor stub
	}

	public SignUpRequestDto(String email, String password, String firstName, String lastName, String contactNo,
			Role role) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.role = Role.USER;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = Role.USER;
	}

	@Override
	public String toString() {
		return "SignUpRequestDto [email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", contactNo=" + contactNo + ", role=" + role + "]";
	}

	

}
