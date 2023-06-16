package com.amusement.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.amusement.model.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer ticketId;
	
	/**
	 * Date for which customer has booked the ticket
	 * It can be future of present
	 */
	private LocalDate visitDate;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Double price;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdOn;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime lastUpdatedOn;
	
	/**
	 * If the visitDate has passed then it will be marked as true
	 */
	@JsonProperty(access = Access.READ_ONLY)
	@Column(nullable = false)
	private Boolean isExpired;
	
	@Min(value = 1, message = "at least 1 person needed to book a ticket")
	private Integer personCount;
	
	
	
	public static Ticket convertToTicket(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket();
		
		ticket.setPersonCount(ticketDTO.getPersonCount());
		ticket.setVisitDate(ticketDTO.getVisitDate());
		
		return ticket;
	}
	
	public static TicketDTO convertToTicketDTO(Ticket ticket) {
		TicketDTO ticketDTO = new TicketDTO();
		
		ticketDTO.setCreatedOn(ticket.getCreatedOn());
		ticketDTO.setIsExpired(ticket.getIsExpired());
		ticketDTO.setLastUpdatedOn(ticket.getLastUpdatedOn());
		ticketDTO.setPersonCount(ticket.getPersonCount());
		ticketDTO.setTicketId(ticket.getTicketId());
		ticketDTO.setVisitDate(ticket.getVisitDate());
		ticketDTO.setPrice(ticket.getPrice());
		
		return ticketDTO;
	}
}
