package com.example.marketmaker.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuoteCalculationEngineImplTest {

    private final QuoteCalculationEngine quoteCalculationEngine;

    public QuoteCalculationEngineImplTest() {
        this.quoteCalculationEngine = new QuoteCalculationEngineImpl();
    }


    @Test
    public void calculateQuotePriceTest(){
        int securityId = 123;
        int refPrice = 100;
        int quantity = 1;

        Assertions.assertEquals(
                100.25d,
                quoteCalculationEngine.calculateQuotePrice(securityId,refPrice,true, quantity));
    }
}