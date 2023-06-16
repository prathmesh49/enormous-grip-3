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
	
	public CustomerDTO updateCustomer(Integer customerId, CustomerDTO updatedCustomer) throws CustomerException;
	
	public Boolean deleteCustomer(Integer customerId) throws CustomerException;
	
	public List<Activity> getActivitySuggestions(Integer customerId, Integer pageNumber, Integer itemsPerPage) throws CustomerException, ActivityException;
	
	public Ticket bookActivityAndIssueTicket(Integer customerId, Integer activityId);

	public void deleteIfDeletionTimePassed();
	
}
