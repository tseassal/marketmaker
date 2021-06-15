package com.example.marketmaker;

import com.example.marketmaker.services.ReferencePriceSource;
import com.example.marketmaker.services.ReferencePriceSourceListener;
import com.example.marketmaker.services.ReferencePriceSourceListenerImpl;
import com.example.marketmaker.services.ReferencePriceSourceRAMImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class MarketmakerApplicationTests {

	private final ReferencePriceSource referencePriceSource;
	private final ReferencePriceSourceListener referencePriceSourceListener;

	@Autowired
	public MarketmakerApplicationTests() {
		this.referencePriceSource = new ReferencePriceSourceRAMImpl();
		this.referencePriceSourceListener = new ReferencePriceSourceListenerImpl();
	}


	@Test
	void contextLoads() {
	}

	@Test
	void priceChangeTest(){
		referencePriceSource.updatePrice(123,1d);
		Assertions.assertEquals(1d, referencePriceSource.get(123));
		//price changes without subscription
		referencePriceSourceListener.referencePriceChanged(123,3d);
		Assertions.assertNotEquals(3d, referencePriceSource.get(123), 0.0);
		//price changes with subscription
		referencePriceSource.subscribe(referencePriceSourceListener);
		referencePriceSourceListener.referencePriceChanged(123,2d);
		Assertions.assertEquals(2d, referencePriceSource.get(123));
	}

}
