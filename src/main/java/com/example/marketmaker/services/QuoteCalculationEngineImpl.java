package com.example.marketmaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class QuoteCalculationEngineImpl implements QuoteCalculationEngine{

    private final double spread = 0.0025;

    @Autowired
    public QuoteCalculationEngineImpl() {
    }

    @Override
    public double calculateQuotePrice(int securityId, double referencePrice, boolean buy, int quantity) {
        if(buy) return referencePrice * quantity * (1d + spread);
        return referencePrice * quantity * (1d - spread);
    }
}
