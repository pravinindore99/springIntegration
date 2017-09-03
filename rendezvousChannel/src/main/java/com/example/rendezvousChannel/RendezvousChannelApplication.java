package com.example.rendezvousChannel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.RendezvousChannel;

import java.util.List;

@SpringBootApplication
public class RendezvousChannelApplication {

	public static void main(String[] args) {
		SpringApplication.run(RendezvousChannelApplication.class, args);
		ConfigurableApplicationContext applicationContext = SpringApplication.run(RendezvousChannelApplication.class, args);
		applicationContext.start();

		ProblemReporter problemReporter = applicationContext.getBean(ProblemReporter.class);
		TicketGenerator ticketGenerator= applicationContext.getBean(TicketGenerator.class);
		TicketReceiver ticketReceiver = applicationContext.getBean(TicketReceiver.class);

		Thread myThread = new Thread(ticketReceiver);
		myThread.start();

		List<Ticket> ticketList = ticketGenerator.createTickets();
		for(Ticket ticket:ticketList){
			problemReporter.openTicket(ticket);
		}

	}

	@Bean("rendezvousChannel")
	public RendezvousChannel channel(){
		return new RendezvousChannel();
	}
}
