package com.mdrsolutions.SpringJmsExample.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class AppController {

    @RequestMapping("/")
    public String appHome(ModelMap map){
        return "index";
    }

    @RequestMapping(path = "/process/order/{orderId}/{customerId}/{bookId}/", method = RequestMethod.GET)
    public @ResponseBody String processOrder(@PathVariable("orderId") String orderId,
                               @PathVariable("customerId") String customerId,
                               @PathVariable("bookId") String bookId )throws JsonMappingException, JsonParseException, IOException {

        return "success";
    }


}
