package com.example.marketmaker.services;

import com.example.marketmaker.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


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
