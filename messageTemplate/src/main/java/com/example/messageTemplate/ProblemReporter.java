
package com.example.messageTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProblemReporter {

   @Autowired
   private MessagingTemplate messagingTemplate;

    public void openTicket(Ticket ticketToBeOpened){
        messagingTemplate.convertAndSend(ticketToBeOpened);
        System.out.println("Message sent!!!" + ticketToBeOpened);
    }
}
