package com.in28minutes.microservices.currencyexchangeservice.kafka;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.in28minutes.microservices.currencyexchangeservice.CurrencyExchange;
import com.in28minutes.microservices.currencyexchangeservice.CurrencyExchangeController;

import io.vavr.collection.Map;

@Component
public class KafkaListeners {
	
	@Autowired
	CurrencyExchangeController c;	
	
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
	
    @KafkaListener(topics = "currencyConversion", groupId = "hello")
    void listener(String data) {
        System.out.println("-------------------------Listener received " + data + " ----------------------------------------------:)");
        HashMap<String,String> myVal= new HashMap();
        CurrencyExchange currencyExchange=c.retrieveExchangeValue(data.split("-")[0],data.split("-")[1]);
        kafkaTemplate.send("currencyExchange", currencyExchange.getConversionMultiple().toString() +"-"+ new Date());
    }

}
