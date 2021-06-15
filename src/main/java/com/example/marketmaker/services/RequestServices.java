package com.example.marketmaker.services;

import com.example.marketmaker.model.Request;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class RequestServices {

    public static Request inputToRequest(String input){
        String[] split = input.split(" ");
        return new Request(Integer.parseInt(split[0]),
                split[1].toLowerCase().equals("buy"),
                Integer.parseInt(split[2]));

    }
}
