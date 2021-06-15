package com.example.marketmaker.quoteServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteCalculationEngineImpl implements QuoteCalculationEngine{

    @Autowired
    public QuoteCalculationEngineImpl() {
    }

    @Override
    public double calculateQuotePrice(int securityId, double referencePrice, boolean buy, int quantity) {
        return 0;
    }
}
