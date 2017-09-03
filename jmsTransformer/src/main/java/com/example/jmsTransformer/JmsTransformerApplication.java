package com.example.jmsTransformer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.jms.JmsMessageDrivenChannelAdapter;
import org.springframework.integration.transformer.MapToObjectTransformer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.*;

import javax.jms.*;
import javax.jms.Message;

@SpringBootApplication
@ImportResource("classpath:bean-context.xml")
public class JmsTransformerApplication {

	private final String QUEUE_NAME= "transformer.queue";

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(JmsTransformerApplication.class, args);
		Queue queue = applicationContext.getBean("getTransformerQueue",Queue.class);
		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

		jmsTemplate.send(queue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("firstName","John");
				message.setString("lastName","Snow");
				message.setString("city","winterfell");
				return message;
			}
		});

		QueueChannel queueChannel = applicationContext.getBean("output",QueueChannel.class);
		org.springframework.messaging.Message<?> reply = queueChannel.receive();
		System.out.println("reply from the final output channel is --->"+reply.getPayload());
	}

	@Bean
	public Queue getTransformerQueue(){
		return new ActiveMQQueue(QUEUE_NAME);
	}

	@Bean
	public DirectChannel map(){
		return new DirectChannel();
	}

	@Bean
	public DirectChannel json(){
		return new DirectChannel();
	}
	@Bean
	public QueueChannel output(){
		return new QueueChannel();
	}
}
