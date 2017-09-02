package com.example.springChannel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.QueueChannel;

import java.util.List;

@SpringBootApplication
@ImportResource("classpath:channel.xml")
public class SpringChannelApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringChannelApplication.class, args);
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

	/*@Bean("ticketChannel")
	public QueueChannel ticketChannel(){
		return new QueueChannel(100);
	}*/

}
