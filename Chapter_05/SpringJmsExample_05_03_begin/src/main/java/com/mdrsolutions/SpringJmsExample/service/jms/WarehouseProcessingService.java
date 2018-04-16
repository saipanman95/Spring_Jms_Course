package com.mdrsolutions.SpringJmsExample.service.jms;

import com.mdrsolutions.SpringJmsExample.pojos.BookOrder;
import com.mdrsolutions.SpringJmsExample.pojos.ProcessedBookOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WarehouseProcessingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProcessingService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    public void processOrder(BookOrder bookOrder, String orderState, String storeId){
        ProcessedBookOrder order = new ProcessedBookOrder(
                bookOrder,
                new Date(),
                new Date()
        );
        if("NEW".equalsIgnoreCase(orderState)){
            add(bookOrder, storeId);
        } else if("UPDATE".equalsIgnoreCase(orderState)){
            update(bookOrder, storeId);
        } else if("DELETE".equalsIgnoreCase(orderState)){
            delete(bookOrder,storeId);
        }

        jmsTemplate.convertAndSend("book.order.processed.queue", order);

    }

    private void add(BookOrder bookOrder, String storeId){
        LOGGER.info("ADDING A NEW ORDER TO DB");
    }
    private void update(BookOrder bookOrder, String storeId){
        LOGGER.info("UPDATING A ORDER TO DB");
    }
    private void delete(BookOrder bookOrder, String storeId){
        LOGGER.info("DELETING ORDER FROM DB");
    }

}
