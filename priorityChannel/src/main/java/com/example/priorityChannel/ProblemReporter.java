package com.example.priorityChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProblemReporter {

    @Autowired
    @Qualifier("priorityChannel")
    private PriorityChannel channel;

    public void openTicket(Ticket ticketToBeOpened){
        Map<String,Object> messageHeaders = new HashMap<>();
        messageHeaders.put("priority",ticketToBeOpened.getPriority().ordinal());
        channel.send(new GenericMessage<Ticket>(ticketToBeOpened,messageHeaders));
        System.out.println("Message sent!!!" + ticketToBeOpened);
    }
}
