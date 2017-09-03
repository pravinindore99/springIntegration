package com.example.priorityChannel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PriorityChannel;

import java.util.List;

@SpringBootApplication
public class PriorityChannelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriorityChannelApplication.class, args);
		ConfigurableApplicationContext applicationContext = SpringApplication.run(PriorityChannelApplication.class, args);
		applicationContext.start();

		ProblemReporter problemReporter = applicationContext.getBean(ProblemReporter.class);
		TicketGenerator ticketGenerator= applicationContext.getBean(TicketGenerator.class);
		TicketReceiver ticketReceiver = applicationContext.getBean(TicketReceiver.class);

		List<Ticket> ticketList = ticketGenerator.createTickets();
		for(Ticket ticket:ticketList){
			problemReporter.openTicket(ticket);
		}

		Thread myThread = new Thread(ticketReceiver);
		myThread.start();
	}

	@Bean("priorityChannel")
	public PriorityChannel channel(){
		return new PriorityChannel();
	}
}
