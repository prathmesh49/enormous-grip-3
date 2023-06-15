package com.amusement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

import com.amusement.exception.ActivityException;
import com.amusement.exception.TicketException;
import com.amusement.model.Activity;
import com.amusement.model.Ticket;
import com.amusement.repository.ActivityRepository;
import com.amusement.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	ActivityRepository activityRepo;
	
	

	@Override
	public Ticket Test(Ticket ticket) throws ActivityException {
		// TODO Auto-generated method stub
		ticketRpo.save(ticket);
//		activityRpo.save(activity);
		return ticket;
	}
	

}
