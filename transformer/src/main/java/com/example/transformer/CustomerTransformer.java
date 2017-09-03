package com.example.transformer;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import java.util.Map;

@MessageEndpoint
public class CustomerTransformer {

    @Transformer(inputChannel = "input" , outputChannel = "output")
    public Customer map(Map<String,String>  message){
        Customer customer = new Customer();
        customer.setFirstName(message.get("firstName"));
        customer.setLastName(message.get("lastName"));
        customer.setCity(message.get("city"));
        customer.setZipCode(message.get("zip"));
        return customer;
    }
}
