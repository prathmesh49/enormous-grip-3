package com.amusement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amusement.DTO.CustomerDTO;
import com.amusement.exception.CustomerException;
import com.amusement.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/")
	public ResponseEntity<CustomerDTO> registerCustomerHandler(@Valid @RequestBody CustomerDTO customerDTO) throws CustomerException{
		
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
	
	@DeleteMapping("/{customerId}")
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
	
	@DeleteMapping("/delete-process")
	public ResponseEntity<Void> deleteAllCustomerIfTimePassedHandler(){
		customerService.deleteIfDeletionTimePassed();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
