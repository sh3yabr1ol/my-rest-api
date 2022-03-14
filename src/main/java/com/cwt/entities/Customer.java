package com.cwt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer custId;
	
	@Column(name = "first_name")
	@Size(min=4, max=32, message="First Name Size should be between 4 and 32 characters")
	@NotBlank(message = "First Name Should Not Be Blank")
	private String firstName;
	
	@Size(min=4, max=32, message="Last Name Size should be between 4 and 32 characters")
	@NotBlank(message = "Last Name Should Not Be Blank")
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank(message = "Email Should Not Be Blank")
	@Pattern(regexp = "^(.+)@(.+)$", message="Please input valid email format")
	@Column(name="email", unique=true, nullable = false)
	private String email;
	
	@NotBlank(message = "Location Should Not Be Blank")
	private String location;
}
