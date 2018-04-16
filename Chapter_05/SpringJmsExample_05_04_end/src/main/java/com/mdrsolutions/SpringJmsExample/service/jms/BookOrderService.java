package com.mdrsolutions.SpringJmsExample.service.jms;

import com.mdrsolutions.SpringJmsExample.pojos.BookOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class BookOrderService {

    private static final String BOOK_QUEUE = "book.order.queue";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public void send(BookOrder bookOrder, String storeId, String orderState){
        jmsTemplate.convertAndSend(BOOK_QUEUE, bookOrder, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setStringProperty("bookOrderId", bookOrder.getBookOrderId());
                message.setStringProperty("storeId", storeId);
                message.setStringProperty("orderState", orderState);
                return message;
            }
        });
    }
}
