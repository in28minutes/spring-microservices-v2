package com.in28minutes.microservices.currencyconversionservice.Message.api;

import com.in28minutes.microservices.currencyconversionservice.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
    @RequestMapping("/app")
    public class CurrencyConversionKafkaController {
        @Autowired
        private CurrencyExchangeProxy proxy;
        @Autowired
        private KafkaTemplate<String,String> kafkaTemplate;

        @Autowired
        private ConsumerSuperThread consumerSuperThread;

        @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
        @PreAuthorize("hasAuthority('course:write')")
        public String calculateCurrencyConversionKafka(
                @PathVariable String from,
                @PathVariable String to,
                @PathVariable BigDecimal quantity
        ) throws ExecutionException, InterruptedException {
            kafkaTemplate.send("currencyConversion", from + "-" + to + "-" + new Date());
            consumerSuperThread.triggerConsumerData(from, to, quantity);
            return "Success";
        }

    }







