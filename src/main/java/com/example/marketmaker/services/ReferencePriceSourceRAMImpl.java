package com.example.marketmaker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReferencePriceSourceRAMImpl implements ReferencePriceSource {

    private final Map<Integer,Double> priceMap;

    @Autowired
    public ReferencePriceSourceRAMImpl() {
        priceMap = new ConcurrentHashMap<>();
        priceMap.put(1, 420d);
        priceMap.put(2, 3.2d);
        priceMap.put(123, 3.2d);
    }

    @Override
    public void subscribe(ReferencePriceSourceListener listener) {
        listener.subscrition(this);
    }

    @Override
    public double get(int securityId) {
        return priceMap.getOrDefault(securityId, 0d);
    }

    @Override
    public void updatePrice(int securityId, double price) {
        priceMap.put(securityId, price);
    }
}
