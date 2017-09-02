package com.example.springChannel;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class EmergencyTicketSelector implements MessageSelector{
    @Override
    public boolean accept(Message<?> message) {
        return (!((Ticket) message.getPayload()).getPriority().equals(Priority.CRITICAL));
    }
}
