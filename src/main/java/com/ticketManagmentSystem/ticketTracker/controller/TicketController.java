package com.ticketManagmentSystem.ticketTracker.controller;

import java.util.List;

import com.ticketManagmentSystem.ticketTracker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketManagmentSystem.ticketTracker.entity.Ticket;

@Controller
@RequestMapping("/admin/tickets")
public class TicketController {
	@Autowired
	private TicketService ticketService;

	@GetMapping("/search")
	public String searchTickets(@RequestParam("query") String query, Model model) {
		
		if (query.trim().isEmpty())
			return "redirect:/admin/tickets";
			
		List<Ticket> searchedTickets = ticketService.searchTickets(query);
		List<Object> searchedTicketsWithoutTimestamp = ticketService.removeTimestamp(searchedTickets);
		model.addAttribute("tickets", searchedTicketsWithoutTimestamp);
		return "tickets/list-tickets";
	}

	@GetMapping("/newTicket")
	public String createticket(Model model) {

		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "tickets/ticket-form";
	}

	@GetMapping("")
	public String listTickets(Model theModel) {

		List<Ticket> tickets = ticketService.viewAlltickets();
		List<Object> ticketsWithoutTimestamp = ticketService.removeTimestamp(tickets);
		theModel.addAttribute("tickets", ticketsWithoutTimestamp);
		return "tickets/list-tickets";
	}

	@PostMapping("/save")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {

		ticketService.saveTicket(ticket);
		return "redirect:/admin/tickets";
	}

	@PostMapping("/update/{id}")
	public String updateTicket(@PathVariable("id") long id, @ModelAttribute("ticket") Ticket ticket) {

		Ticket originalTicket = ticketService.viewticketById(id);
		ticket.setDateCreated(originalTicket.getDateCreated());
		ticketService.updateTicket(ticket);
		return "redirect:/admin/tickets";
	}

	@PostMapping("/delete")
	public String deleteticket(@RequestParam("ticketId") long id) {

		ticketService.removeticketById(id);
		return "redirect:/admin/tickets";
	}

	@PostMapping("/edit/{id}")
	public String updateTicket(@PathVariable("id") long id, Model model) {

		Ticket ticket = ticketService.viewticketById(id);
		model.addAttribute("ticket", ticket);
		return "tickets/update-ticket";
	}

	@PostMapping("/view/{id}")
	public String viewTicket(@PathVariable("id") long id, Model model) {

		Ticket ticket = ticketService.viewticketById(id);
		model.addAttribute("ticket", ticket);
		return "tickets/view-ticket";
	}

}
