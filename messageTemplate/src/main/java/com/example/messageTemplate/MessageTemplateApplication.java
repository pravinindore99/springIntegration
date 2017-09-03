package com.example.messageTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;

import java.util.List;

@SpringBootApplication
public class MessageTemplateApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =SpringApplication.run(MessageTemplateApplication.class, args);

		ProblemReporter problemReporter =
				applicationContext.getBean(ProblemReporter.class);
		TicketReceiver ticketReceiver =
				applicationContext.getBean(TicketReceiver.class);
		TicketGenerator ticketGenerator =
				applicationContext.getBean(TicketGenerator.class);
		List<Ticket> tickets = ticketGenerator.createTickets();
		for (Ticket ticket : tickets) {
			problemReporter.openTicket(ticket);
		}
		Thread consumerThread = new Thread(ticketReceiver);
		consumerThread.start();

	}

	@Bean ("queueChannel")
	public QueueChannel channel(){
		return new QueueChannel();
	}

	@Bean("messagingTemplate")
	public MessagingTemplate messagingTemplate(){
		MessagingTemplate messagingTemplate = new MessagingTemplate();
		messagingTemplate.setDefaultChannel(channel());
		messagingTemplate.setReceiveTimeout(1000);
		return messagingTemplate;
	}
}
