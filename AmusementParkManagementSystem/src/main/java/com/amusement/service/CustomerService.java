package com.amusement.service;

import java.util.List;

import com.amusement.model.Activity;
import com.amusement.model.Customer;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public List<Activity> getActivitySuggestions(Integer customerId);
	
	
	
}
