package com.amusement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amusement.DTO.CustomerDTO;
import com.amusement.exception.CustomerException;
import com.amusement.model.Customer;
import com.amusement.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/")
	public ResponseEntity<CustomerDTO> registerCustomerHandler(@Valid @RequestBody CustomerDTO customerDTO) throws CustomerException{
		customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customerDTO.setCustomerId(null);
		CustomerDTO customer = customerService.registerCustomer(customerDTO);
		
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
//	@GetMapping("/")
//	public ResponseEntity<List<Customer>> getAllCustomersHandler(){
//		
//		List<Customer> customers = customerRepo.findAllNonDeletedCustomers();
//		
//		return new ResponseEntity<>(customers, HttpStatus.OK);
//	}
}
