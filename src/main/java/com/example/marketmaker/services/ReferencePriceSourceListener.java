package com.example.marketmaker.services;

import com.example.marketmaker.services.ReferencePriceSource;

/**
 * Callback interface for {@link ReferencePriceSource}
 */
public interface ReferencePriceSourceListener {

    /**
     * Called when a price has changed.
     *
     * @param securityId security identifier
     * @param price      reference price
     */
    void referencePriceChanged(int securityId, double price);

    /**
     * Called when a price source wants to follow the updates.
     *
     * @param subscriber price source
     */
    void subscrition(ReferencePriceSource subscriber);
}
