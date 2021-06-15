package com.example.marketmaker.model;


public class Request {
    private int securityId;
    private boolean buy;
    private int quantity;

    public Request(int securityId, boolean buy, int quantity) {
        this.securityId = securityId;
        this.buy = buy;
        this.quantity = quantity;
    }

    public int getSecurityId() {
        return securityId;
    }

    public boolean isBuy() {
        return buy;
    }

    public int getQuantity() {
        return quantity;
    }
}
