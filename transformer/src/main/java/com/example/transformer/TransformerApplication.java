package com.example.transformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TransformerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(TransformerApplication.class, args);

		DirectChannel input = applicationContext.getBean("input", DirectChannel.class);
		QueueChannel output = applicationContext.getBean("output", QueueChannel.class);
		Map<String, String> customerMap = new HashMap<String, String>();
		customerMap.put("firstName", "John");
		customerMap.put("lastName", "Smith");
		customerMap.put("city", "Los Angeles");
		customerMap.put("zip", "90064");
		System.out.println("toString(): " + customerMap.toString());
		Message<Map<String, String>> message =MessageBuilder.withPayload(customerMap).build();
		input.send(message);
		Message<?> reply = output.receive();
		System.out.println("received: " + reply.getPayload());
	}

	@Bean
	public DirectChannel input(){
		return new DirectChannel();
	}

	@Bean
	public QueueChannel output(){
		return new QueueChannel();
	}

	//https://dzone.com/articles/message-processing-spring

}
