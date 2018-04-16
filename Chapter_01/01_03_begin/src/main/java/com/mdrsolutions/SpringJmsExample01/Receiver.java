package com.mdrsolutions.SpringJmsExample01;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @JmsListener(destination = "order-queue", containerFactory = "warehouseFactory")
    public void receiveMessage(String message){
        System.out.println("Order received is: " + message);
    }
}
