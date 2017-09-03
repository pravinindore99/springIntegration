package com.example.directChannel;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class TicketMessageHandler implements MessageHandler{

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object receivedPayLoad = message.getPayload();

        if(receivedPayLoad instanceof Ticket){
            handleTicket(receivedPayLoad);
        }
    }

    private void handleTicket(Object receivedPayLoad) {
        System.out.println("Tickets received is ==" + receivedPayLoad);
    }

}
