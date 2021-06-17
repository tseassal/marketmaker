package com.example.marketmaker.services;

import com.example.marketmaker.controller.QuoteController;
import com.example.marketmaker.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestServices {
    private static final Logger logger = LoggerFactory.getLogger(RequestServices.class);

    public static Request inputToRequest(String input){
        String[] split = input.split(" ");
        try{
            return new Request(Integer.parseInt(split[0]),
                    split[1].equalsIgnoreCase("buy"),
                    Integer.parseInt(split[2]));
        }
        catch (Exception e){
            logger.error("Request format error");
        }
        return new Request(0,true,0);


    }
}
