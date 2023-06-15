package com.amusement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amusement.model.Activity;
import com.amusement.model.Customer;
import com.amusement.repository.CustomerRepository;

@Service
public class CustomerServiceImplementation implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer registerCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<Activity> getActivitySuggestions(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
