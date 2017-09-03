package com.example.rendezvousChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.channel.RendezvousChannel;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class TicketReceiver implements Runnable{

    final static int RECEIVE_TIMEOUT = 1000;

    @Autowired
    @Qualifier("rendezvousChannel")
    private RendezvousChannel channel;

    @Override
    public void run() {
        handleTicketMessage();
    }

    private void handleTicketMessage() {
        Message<?> receivedTicket;
        while (true){
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
