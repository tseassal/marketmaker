package com.example.marketmaker.controller;

import com.example.marketmaker.quoteServices.QuoteCalculationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    private final QuoteCalculationEngine quoteCalculationEngine;

    @Autowired
    public QuoteController(QuoteCalculationEngine quoteCalculationEngine) {
        this.quoteCalculationEngine=quoteCalculationEngine;
    }

    @RequestMapping(value = "quote", method = RequestMethod.GET)
    public ResponseEntity<Double> getQuote(String request){
        
        double res = quoteCalculationEngine.calculateQuotePrice(1,0.2,true,1);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }
}
