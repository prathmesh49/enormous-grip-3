package com.amusement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amusement.DTO.CustomerDTO;
import com.amusement.DTO.TicketDTO;
import com.amusement.exception.ActivityException;
import com.amusement.exception.CustomerException;
import com.amusement.exception.TicketException;
import com.amusement.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private CustomerService customerService;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping({"/", ""})
	public ResponseEntity<CustomerDTO> registerCustomerHandler(
			@Valid @RequestBody CustomerDTO customerDTO) throws CustomerException{

		customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customerDTO.setCustomerId(null);
		CustomerDTO customer = customerService.registerCustomer(customerDTO);
		
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> updateCustomerDetailsHandler(@PathVariable Integer customerId, 
			@Valid @RequestBody CustomerDTO customerDTO) throws CustomerException{
		
		return new ResponseEntity<>(customerService.updateCustomer(customerId, customerDTO), HttpStatus.OK);
	}
	
	@DeleteMapping({"/{customerId}", "/{customerId}/"})
	public ResponseEntity<String> deleteCustomerHandler(@PathVariable Integer customerId) throws CustomerException{
		Boolean deleteStatus = customerService.deleteCustomer(customerId);
		
		HttpStatus status;
		String message;
		
		if(deleteStatus) {
			status = HttpStatus.OK;
			message = "Account deleted successfully, You can create another account with same email after 30 minutes";
		}else {
			status = HttpStatus.BAD_REQUEST;
			message = "Something went wrong";
		}
		
		return new ResponseEntity<>(message, status);
	}
	
	@PostMapping("/{customerId}/{activityId}")
	public ResponseEntity<TicketDTO> bookActivityAndIssueTicketHandler(
			@PathVariable Integer customerId,
			@PathVariable Integer activityId,
			@Valid @RequestBody TicketDTO ticketDTO) throws ActivityException, TicketException, CustomerException{
		
		return new ResponseEntity<>(customerService.bookActivityAndIssueTicket(customerId, activityId, ticketDTO), HttpStatus.OK);
	}
	
}
