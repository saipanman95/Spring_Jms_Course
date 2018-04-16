package com.mdrsolutions.SpringJmsExample.service.jms;

import com.mdrsolutions.SpringJmsExample.pojos.BookOrder;
import com.mdrsolutions.SpringJmsExample.pojos.ProcessedBookOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WarehouseProcessingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseProcessingService.class);
    private static final String PROCESSED_QUEUE = "book.order.processed.queue";
    private static final String CANCELED_QUEUE = "book.order.canceled.queue";

    @Transactional
    public JmsResponse<Message<ProcessedBookOrder>> processOrder(BookOrder bookOrder, String orderState, String storeId){

        Message<ProcessedBookOrder> message;

        if("NEW".equalsIgnoreCase(orderState)){
            message = add(bookOrder, storeId);
            return JmsResponse.forQueue(message, PROCESSED_QUEUE);
        } else if("UPDATE".equalsIgnoreCase(orderState)){
            message = update(bookOrder, storeId);
            return JmsResponse.forQueue(message, PROCESSED_QUEUE);
        } else if("DELETE".equalsIgnoreCase(orderState)){
            message = delete(bookOrder,storeId);
            return JmsResponse.forQueue(message, CANCELED_QUEUE);
        } else{
            throw new IllegalArgumentException("WarehouseProcessingService.processOrder(...) - orderState does not match expected criteria!");
        }

    }

    private Message<ProcessedBookOrder> add(BookOrder bookOrder, String storeId){
        LOGGER.info("ADDING A NEW ORDER TO DB");
        //TODO - some type of db operation
        return build(new ProcessedBookOrder(
                bookOrder,
                new Date(),
                new Date()
        ), "ADDED", storeId);
    }
    private Message<ProcessedBookOrder> update(BookOrder bookOrder, String storeId){
        LOGGER.info("UPDATING A ORDER TO DB");
        //TODO - some type of db operation
        return build(new ProcessedBookOrder(
                bookOrder,
                new Date(),
                new Date()
        ), "UPDATED", storeId);
    }
    private Message<ProcessedBookOrder> delete(BookOrder bookOrder, String storeId){
        LOGGER.info("DELETING ORDER FROM DB");
        //TODO - some type of db operation
        return build(new ProcessedBookOrder(
                bookOrder,
                new Date(),
                null
        ), "DELETED", storeId);
    }

    private Message<ProcessedBookOrder> build(ProcessedBookOrder bookOrder, String orderState, String storeId){
        return MessageBuilder
                .withPayload(bookOrder)
                .setHeader("orderState", orderState)
                .setHeader("storeId", storeId)
                .build();
    }
}
