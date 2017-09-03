package com.example.directChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;

import java.util.List;

@SpringBootApplication
public class DirectChannelApplication {


	public static void main(String[] args) {
		SpringApplication.run(DirectChannelApplication.class, args);
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DirectChannelApplication.class, args);
		applicationContext.start();

		ProblemReporter problemReporter =
				applicationContext.getBean(ProblemReporter.class);
		TicketGenerator ticketGenerator =
				applicationContext.getBean(TicketGenerator.class);
		TicketMessageHandler ticketMessageHandler =
				applicationContext.getBean(TicketMessageHandler.class);
		DirectChannel channel =
				applicationContext.getBean("directChannel", DirectChannel.class);
		channel.subscribe(ticketMessageHandler);
		List<Ticket> tickets = ticketGenerator.createTickets();
		for (Ticket ticket : tickets) {
			problemReporter.openTicket(ticket);
		}
	}


	@Bean("directChannel")
	public DirectChannel channel(){
		return new DirectChannel();
	}
}
