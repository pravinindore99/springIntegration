package com.example.springChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class TicketReceiver implements Runnable{

    final static int RECEIVE_TIMEOUT = 1000;

    @Autowired
    @Qualifier("ticketChannel")
    private QueueChannel channel;

    @Autowired
    private EmergencyTicketSelector emergencyTicketSelector;

    @Override
    public void run() {
        handleTicketMessage();
    }

    void handleEmergencyTickets(List<Message<?>> highPriorityTicketMessages) {
        Assert.notNull(highPriorityTicketMessages);
        for (Message<?> ticketMessage : highPriorityTicketMessages) {
            handleTicket((Ticket) ticketMessage.getPayload());
        }
    }

    private void handleTicketMessage() {
        Message<?> receivedTicket;
        while (true){

            List<Message<?>> emergencyTickets = channel.purge(emergencyTicketSelector);
            handleEmergencyTickets(emergencyTickets);
            receivedTicket =channel.receive(RECEIVE_TIMEOUT);
            if(receivedTicket!=null){
                handleTicket((Ticket) receivedTicket.getPayload());
            }else{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void handleTicket(Ticket ticket){
        System.out.println("Ticket that was recieved is " + ticket);
    }
}
