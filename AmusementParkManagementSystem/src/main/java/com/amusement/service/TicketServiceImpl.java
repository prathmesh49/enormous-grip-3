package com.amusement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.amusement.exception.ActivityException;
import com.amusement.exception.TicketException;
import com.amusement.model.Activity;
import com.amusement.model.Ticket;
import com.amusement.repository.ActivityRepo;
import com.amusement.repository.TicketRepo;


public class TicketServiceImpl implements TicketService{

	@Autowired
	TicketRepo ticketRpo;
	
	@Autowired
	ActivityRepo activityRpo;
	
	@Override
	public Ticket createTicket(Ticket ticket, Integer activity_id) throws ActivityException{
		// TODO Auto-generated method stub
		Optional<Activity> opt =  activityRpo.findById(activity_id);
		if(opt.isPresent()) {
			Activity activity =  opt.get();
			activity.getTickets().add(ticket);
			ticket.setActivity(activity);
			ticketRpo.save(ticket);
			activityRpo.save(activity);
			return ticket;
		}
		else {
			throw new ActivityException("No such activity present");
		}
	}
	
	public Ticket deleteTicket(Integer ticket_id)throws TicketException {
	 	Optional<Ticket> opt =  ticketRpo.findById(ticket_id);
	 	if(opt.isPresent()) {
	 		Ticket t1 =  opt.get();
	 		t1.setDeleted(true);
	 		return t1;
	 	}
	 	else {
	 		throw new TicketException("No ticket found of this ID "+ticket_id);
	 	}
	}
	
	public List<Ticket> viewAllTickets() throws TicketException{
		
		List<Ticket> ticketList= ticketRpo.viewAllTickets();
		if(ticketList.size()==0) {
			throw new TicketException("No tickets available");
		}
		else {
			return ticketList;
		}
		
	}

}
