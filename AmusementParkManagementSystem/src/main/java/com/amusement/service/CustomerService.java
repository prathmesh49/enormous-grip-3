package com.amusement.service;

import java.util.List;

import com.amusement.DTO.ActivityDTO;
import com.amusement.DTO.CustomerDTO;
import com.amusement.DTO.TicketDTO;
import com.amusement.exception.ActivityException;
import com.amusement.exception.CustomerException;
import com.amusement.exception.TicketException;

public interface CustomerService {
	
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) throws CustomerException;
	
	public CustomerDTO updateCustomer(Integer customerId, CustomerDTO updatedCustomer) throws CustomerException;
	
	public Boolean deleteCustomer(Integer customerId) throws CustomerException;
	
	public List<ActivityDTO> getActivitySuggestions(Integer customerId, Integer pageNumber, Integer itemsPerPage) throws CustomerException, ActivityException;
	
	public TicketDTO bookActivityAndIssueTicket(Integer customerId, Integer activityId, TicketDTO ticketDTO) throws ActivityException, TicketException, CustomerException;
	
	
}
