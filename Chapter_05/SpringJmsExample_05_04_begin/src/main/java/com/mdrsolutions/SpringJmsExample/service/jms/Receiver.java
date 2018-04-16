package com.mdrsolutions.SpringJmsExample.service.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Receiver {

    @JmsListener(destination = "order-queue")
    public void receiveMessage(String order){

        System.out.println("Order Recieved = " + order);
    }


}
