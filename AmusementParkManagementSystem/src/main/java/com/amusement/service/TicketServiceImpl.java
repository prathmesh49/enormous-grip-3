package com.amusement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.amusement.exception.ActivityException;
import com.amusement.exception.TicketException;
import com.amusement.model.Activity;
import com.amusement.model.Ticket;
import com.amusement.repository.ActivityRepository;
import com.amusement.repository.TicketRepository;


public class TicketServiceImpl implements TicketService{

	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	ActivityRepository activityRepo;
	
	

}
