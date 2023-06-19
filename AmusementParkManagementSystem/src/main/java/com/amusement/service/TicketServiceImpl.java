package com.amusement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amusement.DTO.TicketDTO;
import com.amusement.exception.ActivityException;
import com.amusement.exception.CustomerException;
import com.amusement.exception.TicketException;
import com.amusement.model.Activity;
import com.amusement.model.Customer;
import com.amusement.model.Ticket;
import com.amusement.repository.ActivityRepository;
import com.amusement.repository.CustomerRepository;
import com.amusement.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketRepository ticketRepo;
	
	@Autowired
	private ActivityRepository activityRepo;
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public TicketDTO createTicket(Integer customerId, Integer activityId, TicketDTO ticketDTO)
			throws ActivityException, CustomerException, TicketException {
		// TODO Auto-generated method stub
		Optional<Activity> activity = activityRepo.findById(activityId);
		Optional<Customer> customer = customerRepo.findById(customerId);
		
		if(activity.isEmpty() || (activity.isPresent() && activity.get().getIsDeleted() == true)) throw new ActivityException("Activity with activityId: "+ activityId +" not found");
		if(customer.isEmpty() || (customer.isPresent() && customer.get().getIsDeleted() == true)) throw new CustomerException("User not found with userId: "+customerId);
		
		if(this.isVisitDateValid(ticketDTO.getVisitDate())) {
			
			/**
			 * logic to check whether seats are available or not
			 */
			Integer seats = activity.get().getPersonCapacity();
			
			Integer value = ticketRepo.getPersonCountOfActivityForDate(ticketDTO.getVisitDate(), activityId);
			if(value == null) value = 0;
			
			Integer availableSeats = activity.get().getPersonCapacity() - value;
			
			if(availableSeats == 0) throw new TicketException("All tickets are sold for the day");
			if(availableSeats < ticketDTO.getPersonCount()) throw new TicketException("Only " + availableSeats + " seats are remaining");
			
			/**
			 * booking the ticket
			 */
			Ticket ticket = TicketDTO.convertToTicket(ticketDTO);
			
			ticket.setIsExpired(false);
			ticket.setActivity(activity.get());
			ticket.setCustomer(customer.get());
			ticket.setPrice(activity.get().getPrice()*ticketDTO.getPersonCount());
			
			Ticket saved = ticketRepo.save(ticket);
			
			/**
			 * adding saved ticket to customer and activity
			 */
			customer.get().getTickets().add(saved);
			activity.get().getTickets().add(saved);
			
			return TicketDTO.convertToTicketDTO(ticket);
			
		}else
			throw new TicketException("Visiting date should be present or future");
		
	}
	
	@Override
	public TicketDTO updateTicket(Integer customerId, Integer ticketId, TicketDTO ticketDTO) throws CustomerException, TicketException {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepo.findById(customerId);
		Optional<Ticket> ticketOpt = ticketRepo.findById(ticketId);
		
		if(customer.isEmpty() || (customer.isPresent() && customer.get().getIsDeleted() == true)) throw new CustomerException("User not found with userId: "+customerId);
		if(ticketOpt.isEmpty()) throw new CustomerException("Ticket not found with ticketId: "+ ticketId);
		
		if(ticketOpt.get().getCustomer().getCustomerId() != customerId) throw new CustomerException("Not your ticket");
		
		if(ticketOpt.get().getVisitDate().isBefore(LocalDate.now())) ticketOpt.get().setIsExpired(true);
		ticketRepo.save(ticketOpt.get());
		
		if(ticketOpt.get().getIsExpired() == true) throw new TicketException("can't modify, Ticket is expired");
		
		Ticket ticket = ticketOpt.get();
		
		Double perPersonPrice = ticket.getPrice() / ticket.getPersonCount();
		Double updatedPrice = ticketDTO.getPersonCount() * perPersonPrice;

		ticket.setPrice(updatedPrice);
		ticket.setPersonCount(ticketDTO.getPersonCount());
		
		if(this.isVisitDateValid(ticketDTO.getVisitDate())) {
			
			ticket.setVisitDate(ticketDTO.getVisitDate());
		}else
			throw new TicketException("Visiting date should be present or future");
		
		
		return TicketDTO.convertToTicketDTO(ticketRepo.save(ticket));
	}
	
	@Override
	public TicketDTO getTicketById(Integer customerId, Integer ticketId) throws CustomerException, TicketException{
		Optional<Customer> customer = customerRepo.findById(customerId);
		Optional<Ticket> ticketOpt = ticketRepo.findById(ticketId);
		
		if(customer.isEmpty() || (customer.isPresent() && customer.get().getIsDeleted() == true)) throw new CustomerException("User not found with userId: "+customerId);
		if(ticketOpt.isEmpty()) throw new CustomerException("Ticket not found with ticketId: "+ ticketId);
		
		if(ticketOpt.get().getCustomer().getCustomerId() != customerId) throw new CustomerException("Not your ticket");
		
		if(ticketOpt.get().getVisitDate().isBefore(LocalDate.now())) ticketOpt.get().setIsExpired(true);
		ticketRepo.save(ticketOpt.get());
		
		return TicketDTO.convertToTicketDTO(ticketOpt.get());
	}

	@Override
	public Boolean deleteTicket(Integer customerId, Integer ticketId) throws CustomerException, TicketException {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepo.findById(customerId);
		Optional<Ticket> ticketOpt = ticketRepo.findById(ticketId);
		
		if(customer.isEmpty() || (customer.isPresent() && customer.get().getIsDeleted() == true)) throw new CustomerException("User not found with userId: "+customerId);
		if(ticketOpt.isEmpty()) throw new CustomerException("Ticket not found with ticketId: "+ ticketId);
		
		if(ticketOpt.get().getCustomer().getCustomerId() != customerId) throw new CustomerException("Not your ticket");

		ticketRepo.deleteTicket(ticketId);
		
		/**
		 * it will not visible immediately in the database for that reason these falsy values are passed
		 */
		if(ticketRepo.findById(ticketId).isEmpty()) return false;
		else return true;
	}

	@Override
	public List<TicketDTO> viewAllTickets(Integer pageNumber, Integer itemsPerPage) throws TicketException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketDTO> getTicketBookingHistory(Integer customerId, Integer pageNumber, Integer itemsPerPage)
			throws TicketException, CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepo.findById(customerId);
		
		if(customer.isEmpty() || (customer.isPresent() && customer.get().getIsDeleted() == true)) throw new CustomerException("User not found with userId: "+customerId);
		
		List<Ticket> t = ticketRepo.findAll(PageRequest.of(pageNumber-1, itemsPerPage)).getContent();
		if(t.isEmpty()) throw new TicketException("Don't have Ticket booking History");
		
		List<TicketDTO> tickets = new ArrayList<>();
		t.forEach(i -> tickets.add(TicketDTO.convertToTicketDTO(i)));
		
		return tickets;
	}

	
	
	/**
	 * method to check is customer provided a valid visiting date or not
	 * @param LocalDate
	 * @return boolean
	 */
	private boolean isVisitDateValid(LocalDate date) {
		
		if(date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now())) return true;
		else return false;
	}
	
}
