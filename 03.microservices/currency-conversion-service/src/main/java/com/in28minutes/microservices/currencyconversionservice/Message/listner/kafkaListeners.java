package com.in28minutes.microservices.currencyconversionservice.Message.listner;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class kafkaListeners {

    @KafkaListener(topics = "currencyExchange", groupId = "hello")
    void listener(String data) {
        System.out.println("Listener received of currencyExchange: " + data + " :)");
    }
}
