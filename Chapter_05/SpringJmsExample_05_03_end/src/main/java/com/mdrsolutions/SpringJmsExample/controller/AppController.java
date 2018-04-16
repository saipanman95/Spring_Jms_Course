package com.mdrsolutions.SpringJmsExample.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mdrsolutions.SpringJmsExample.pojos.Book;
import com.mdrsolutions.SpringJmsExample.pojos.BookOrder;
import com.mdrsolutions.SpringJmsExample.pojos.Customer;
import com.mdrsolutions.SpringJmsExample.service.jms.BookOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private BookOrderService bookOrderService;

    List<Book> books = Arrays.asList(
                                     new Book("jpw-1234", "Lord of the Flies"),
                                     new Book("uyh-2345", "Being and Nothingness"),
                                     new Book("iuhj-87654","At Sea and Lost"));


    List<Customer> customers = Arrays.asList(
                                             new Customer("mr-1234", "Michael Rodgers"),
                                             new Customer("jp-2345", "Jeff Peek"),
                                             new Customer("sm-8765", "Steve McClarney")
                                             );

    @RequestMapping("/")
    public String appHome(ModelMap map){


        map.addAttribute("customers", customers);
        map.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(path = "/process/store/{storeId}/order/{orderId}/{customerId}/{bookId}/{orderState}/", method = RequestMethod.GET)
    public @ResponseBody String processOrder(@PathVariable("storeId") String storeId,
                                             @PathVariable("orderId") String orderId,
                                             @PathVariable("customerId") String customerId,
                                             @PathVariable("bookId") String bookId,
                                             @PathVariable("orderState") String orderState )throws JsonMappingException, JsonParseException, IOException {

        try {
            LOGGER.info("StoredId is = {}", storeId);
            bookOrderService.send(build(customerId, bookId, orderId), storeId, orderState);
        } catch (Exception exception) {
            return "Error occurred!" + exception.getLocalizedMessage();
        }
        return "Order sent to warehouse for bookId = " + bookId + " from customerId = " + customerId + " successfully processed!";
    }

    private BookOrder build(String customerId, String bookId, String orderId){
        Book myBook = null;
        Customer myCustomer = null;

        for(Book bk : books){
            if(bk.getBookId().equalsIgnoreCase(bookId)){
                myBook = bk;
            }
        }
        if(null == myBook){
            throw new IllegalArgumentException("Book selected does not exist in inventory!");
        }

        for(Customer ct : customers){
            if(ct.getCustomerId().equalsIgnoreCase(customerId)){
                myCustomer = ct;
            }
        }

        if(null == myCustomer){
            throw new IllegalArgumentException("Customer selected does not appear to be valid!");
        }

        return new BookOrder(orderId, myBook, myCustomer);
    }


}
