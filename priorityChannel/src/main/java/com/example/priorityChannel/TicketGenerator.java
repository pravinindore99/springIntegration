package com.example.priorityChannel;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class TicketGenerator {

    private long nextTicketId;
    public TicketGenerator() {
        this.nextTicketId = 1000l;
    }

    public List<Ticket> createTickets() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(createLowPriorityTicket());
        tickets.add(createLowPriorityTicket());
        tickets.add(createLowPriorityTicket());
        tickets.add(createLowPriorityTicket());
        tickets.add(createLowPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createMediumPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createHighPriorityTicket());
        tickets.add(createEmergencyTicket());
        tickets.add(createEmergencyTicket());
        tickets.add(createEmergencyTicket());
        tickets.add(createEmergencyTicket());
        tickets.add(createEmergencyTicket());
        return tickets;
    }
    Ticket createEmergencyTicket() {
        return createTicket(Priority.CRITICAL,
                "Urgent problem. Fix immediately or revenue will be lost!");
    }
    Ticket createHighPriorityTicket() {
        return createTicket(Priority.HIGH,
                "Serious issue. Fix immediately.");
    }
    Ticket createMediumPriorityTicket() {
        return createTicket(Priority.MEDIUM,
                "There is an issue; take a look whenever you have time.");
    }
    Ticket createLowPriorityTicket() {
        return createTicket(Priority.LOW,
                "Some minor problems have been found.");
    }
    Ticket createTicket(Priority priority, String description) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(nextTicketId++);
        ticket.setPriority(priority);
        ticket.setIssueDate(GregorianCalendar.getInstance());
        ticket.setDescription(description);
        return ticket;
    }
}
