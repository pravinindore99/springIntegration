package com.example.springChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProblemReporter {

    @Autowired
    @Qualifier("ticketChannel")
    private QueueChannel channel;

    public void openTicket(Ticket ticketToBeOpened){
        channel.send(MessageBuilder.withPayload(ticketToBeOpened).build());
        System.out.println("Message sent!!!" + ticketToBeOpened);
    }
}
