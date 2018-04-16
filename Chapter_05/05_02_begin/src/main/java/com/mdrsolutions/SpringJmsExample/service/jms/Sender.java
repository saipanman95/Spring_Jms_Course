package com.mdrsolutions.SpringJmsExample.service.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Sender {

    @Autowired
    JmsTemplate jmsTemplate;

    @Transactional
    public void sendMessage(String destination, String message){
        jmsTemplate.convertAndSend(destination, message);
    }
}
