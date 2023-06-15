package com.amusement.service;

import java.util.List;

import com.amusement.DTO.CustomerDTO;
import com.amusement.exception.ActivityException;
import com.amusement.exception.CustomerException;
import com.amusement.model.Activity;
import com.amusement.model.Customer;
import com.amusement.model.Ticket;

public interface CustomerService {
	
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) throws CustomerException;
	
	public Customer updateCustomer(Integer customerId, CustomerDTO newCustomer) throws CustomerException;
	
	public Customer deleteCustomer(Integer customerId) throws CustomerException;
	
	public List<Activity> getActivitySuggestions(Integer customerId) throws CustomerException, ActivityException;
	
//	public List<Activity> getAllAvailableActivities() throws ActivityException;
	
	public Ticket bookActivityAndIssueTicket(Integer customerId, Integer activityId);

}
