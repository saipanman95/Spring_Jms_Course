package com.mdrsolutions.SpringJmsExample.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class BookOrderProcessingMessageListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookOrderProcessingMessageListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            String text = ((TextMessage) message).getText();
            LOGGER.info(text);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
