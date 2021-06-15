package com.example.marketmaker.services;

import com.example.marketmaker.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequestServicesTest {

    private static final Logger logger = LoggerFactory.getLogger(RequestServicesTest.class);

    @Autowired
    public RequestServicesTest() {
    }

    @Test
    public void inputToRequestTest(){
        String input = "123 buy 100";
        Request request = RequestServices.inputToRequest(input);
        Request expected = new Request(123, true, 100);
        Assertions.assertEquals(expected.getQuantity(), request.getQuantity());
        Assertions.assertEquals(expected.getSecurityId(), request.getSecurityId());
        Assertions.assertEquals(expected.isBuy(), request.isBuy());
    }
}
