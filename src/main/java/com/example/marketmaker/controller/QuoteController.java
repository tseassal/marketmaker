package com.example.marketmaker.controller;

import com.example.marketmaker.model.Request;
import com.example.marketmaker.services.QuoteCalculationEngine;
import com.example.marketmaker.services.ReferencePriceSource;
import com.example.marketmaker.services.RequestServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
    private static final Logger logger = LoggerFactory.getLogger(QuoteController.class);

    private final QuoteCalculationEngine quoteCalculationEngine;
    private final ReferencePriceSource referencePriceSource;

    @Autowired
    public QuoteController(QuoteCalculationEngine quoteCalculationEngine, ReferencePriceSource referencePriceSource) {
        this.quoteCalculationEngine=quoteCalculationEngine;
        this.referencePriceSource=referencePriceSource;
    }

    @RequestMapping(value = "quote", method = RequestMethod.GET)
    public ResponseEntity<Double> getQuoteEntry(String request){
        //for debug testing purpose
        logger.info("Receive request : {}", request);
        return new ResponseEntity<>(getQuote(request), HttpStatus.OK);

    }

    public double getQuote(String request){
        Request input = RequestServices.inputToRequest(request);
        if(input.getQuantity()!=0) return quoteCalculationEngine.calculateQuotePrice(
                input.getSecurityId(),
                referencePriceSource.get(input.getSecurityId()),
                input.isBuy(),
                input.getQuantity());
        return 0;

    }
}
