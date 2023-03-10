package com.app.pojos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client extends BaseEntity {
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 20)
	private Address address;
	@Column(length = 10)
	private String contactNo;
	@Column(length = 30)
	private String email;
	@Column(name = "enroll_date")
	private LocalDate enrollDate;
	@Column(length = 300)
	private String imagePath;

	private Double amount;
	@Enumerated
	private Status status;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Payment> payments = new HashSet<Payment>();

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "property_id")
	private Property property;

	@ManyToMany(mappedBy = "clients", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JsonIgnore
	private Set<Owner> Owners = new HashSet<>();;

}
