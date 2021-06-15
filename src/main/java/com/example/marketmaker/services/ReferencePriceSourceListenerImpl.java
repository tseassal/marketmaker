package com.example.marketmaker.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class ReferencePriceSourceListenerImpl implements ReferencePriceSourceListener{

    private List<ReferencePriceSource> subscribers;

    @Autowired
    public ReferencePriceSourceListenerImpl() {
        subscribers = new LinkedList<>();
    }

    @Override
    public void referencePriceChanged(int securityId, double price) {
        for(ReferencePriceSource source : subscribers){
            source.updatePrice(securityId, price);
        }
    }

    @Override
    public void subscrition(ReferencePriceSource subscriber){
        subscribers.add(subscriber);
    }
}
