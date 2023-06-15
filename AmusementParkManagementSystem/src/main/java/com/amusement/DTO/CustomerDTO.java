package com.amusement.DTO;

import com.amusement.model.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer customerId;
	
	@Size(min = 2, max = 15, message = "{invalid.username}")
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String address;
	
	@Size(min = 10, max = 10, message = "{invalid.mobileNumber}")
	@Pattern(regexp = "^[6-9]\\d{9}", message = "{invalid.mobileNumber}")
	private String mobileNumber;
	
	@Email(message = "{invalid.email}")
	private String email;
	
	
	public static Customer convertToCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		
		customer.setEmail(customerDTO.getEmail());
		customer.setAddress(customerDTO.getAddress());
		customer.setMobileNumber(customerDTO.getMobileNumber());
		customer.setPassword(customerDTO.getPassword());
		customer.setUsername(customerDTO.getUsername());
		
		return customer;
	}
	
	public static CustomerDTO convertToCustomerDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setMobileNumber(customer.getMobileNumber());
		customerDTO.setUsername(customer.getUsername());
		customerDTO.setCustomerId(customer.getCustomerId());
		
		return customerDTO;
	}
}
