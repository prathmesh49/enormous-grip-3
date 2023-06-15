package com.amusement.service;

import java.util.List;

import com.amusement.exception.ActivityException;
import com.amusement.exception.TicketException;
import com.amusement.model.Ticket;

public interface TicketService {


//	public Ticket createTicket(Ticket ticket, Integer activity_id) throws ActivityException;
//	
//	public Ticket deleteTicket(Integer ticket_id) throws TicketException ;
//	
//	public List<Ticket> viewAllTickets() throws TicketException;

	public Ticket createTicket(Ticket ticket, Integer activity_id) throws ActivityException;
	
	public Ticket Test(Ticket ticket) throws ActivityException;
	
	public Ticket deleteTicket(Integer ticket_id) throws TicketException ;
	
	public List<Ticket> viewAllTickets() throws TicketException;

}
