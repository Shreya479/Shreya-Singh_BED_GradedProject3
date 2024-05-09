package com.ticketManagmentSystem.ticketTracker.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketManagmentSystem.ticketTracker.dao.TicketRepository;
import com.ticketManagmentSystem.ticketTracker.entity.Ticket;
import com.ticketManagmentSystem.ticketTracker.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> searchTickets(String query) {

		List<Ticket> tickets = ticketRepository.searchTickets(query);
		return tickets;
	}

	@Override
	public Ticket viewticketById(long id) {

		Ticket ticket = ticketRepository.findById(id).get();

		if (ticket == null)
			throw new RuntimeException("Did not find the ticket id: " + id);
		else
			return ticket;

	}

	@Override
	public List<Ticket> viewAlltickets() {

		return ticketRepository.findAll();
	}

	@Override
	public void saveTicket(Ticket ticket) {

		String timestamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());

		ticket.setDateCreated(timestamp);
		ticket.setDateUpdated(timestamp);

		ticketRepository.save(ticket);
	}

	@Override
	public void updateTicket(Ticket ticket) {

		String timestamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());

		ticket.setDateUpdated(timestamp);
		ticketRepository.save(ticket);
	}

	@Override
	public void removeticketById(long id) {
		ticketRepository.deleteById(id);
	}

	/**
	 * @param tickets
	 * @return
	 */
	@Override
	public List<Object> removeTimestamp(List<Ticket> tickets) {
		return null;
	}

}
