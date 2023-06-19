package com.amusement.service;

import java.util.List;

import com.amusement.DTO.TicketDTO;
import com.amusement.exception.ActivityException;
import com.amusement.exception.CustomerException;
import com.amusement.exception.TicketException;

public interface TicketService {

	public TicketDTO createTicket(Integer customerId, Integer activityId, TicketDTO ticketDTO) throws ActivityException, CustomerException, TicketException;

	public TicketDTO updateTicket(Integer customerId, Integer ticketId, TicketDTO ticketDTO) throws CustomerException, TicketException;
	
	public TicketDTO getTicketById(Integer customerId, Integer ticketId) throws CustomerException, TicketException;
	
	public Boolean deleteTicket(Integer customerId, Integer ticketId) throws CustomerException, TicketException;
	
	public List<TicketDTO> viewAllTickets(Integer pageNumber, Integer itemsPerPage) throws TicketException;
	
	public List<TicketDTO> getTicketBookingHistory(Integer customerId, Integer pageNumber, Integer itemsPerPage) throws TicketException, CustomerException;

}
