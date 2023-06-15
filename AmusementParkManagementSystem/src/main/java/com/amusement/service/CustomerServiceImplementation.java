package com.amusement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amusement.DTO.CustomerDTO;
import com.amusement.exception.ActivityException;
import com.amusement.exception.CustomerException;
import com.amusement.model.Activity;
import com.amusement.model.Customer;
import com.amusement.model.Role;
import com.amusement.model.Ticket;
import com.amusement.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) throws CustomerException {
		// TODO Auto-generated method stub
		
		Optional<Customer> existingCustomer = customerRepository.findByEmail(customerDTO.getEmail());
		if(existingCustomer.isPresent()) throw new CustomerException("Email is already in use");
		
		Customer customer = customerDTO.convertToCustomer(customerDTO);
		customer.setRole(Role.USER);
		customer.setIsDeleted(false);
		
		Customer saved = customerRepository.save(customer);
		
		return customerDTO.convertToCustomerDTO(saved);
	}

	@Override
	public Customer updateCustomer(Integer customerId, CustomerDTO newCustomer) throws CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		if(customer.isEmpty()) throw new CustomerException("Customer not found with customerId: "+customerId);
		
		customer.get().setAddress(newCustomer.getAddress());
		customer.get().setUsername(newCustomer.getUsername());
		return null;
	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getActivitySuggestions(Integer customerId) throws CustomerException, ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket bookActivityAndIssueTicket(Integer customerId, Integer activityId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
