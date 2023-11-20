package com.in28minutes.microservices.currencyconversionservice.Message.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public MessageController(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public MessageController(){
    }

    @PostMapping
    public void publish(@RequestBody MessageRequest request){
        kafkaTemplate.send("xecTest",request.getMessage());
    }
}
