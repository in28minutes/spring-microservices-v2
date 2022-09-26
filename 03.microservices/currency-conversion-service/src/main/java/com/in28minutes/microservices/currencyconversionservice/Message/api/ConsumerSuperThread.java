package com.in28minutes.microservices.currencyconversionservice.Message.api;

import com.in28minutes.microservices.currencyconversionservice.CurrencyConversion;
import com.in28minutes.microservices.currencyconversionservice.Message.KafkaConsumerConfig;
import io.jsonwebtoken.lang.Collections;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConsumerSuperThread extends Thread {

    public ConsumerSuperThread() {
    }

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    public Map<String,String> MY_MAP=new HashMap<>();

    public void triggerConsumerData(String from,String to,BigDecimal quantity){
       if(Collections.isEmpty(MY_MAP)) {
           this.start();
       }else {
           BigDecimal multiple = BigDecimal.valueOf(Long.parseLong(MY_MAP.get("multiply")));

           CurrencyConversion hello_from_messaging_kafka = new CurrencyConversion(1L,
                   from, to, quantity,
                   multiple,
                   quantity.multiply(multiple),
                   "Hello from Messaging kafka");
           System.out.println(hello_from_messaging_kafka);
           MY_MAP.clear();
       }
    }
    @Override
    public void run() {
        BigDecimal multiple = BigDecimal.ZERO;
        KafkaConsumer<String, String> consumer = kafkaConsumerConfig.getConsumer();
        try {
            while (MY_MAP.isEmpty()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> record : records)
                {
                    logger.info("topic = %s, partition = %d, offset = %d, multipleKey = %s multipleValue = %s",
                            record.topic(), record.partition(), record.offset(),
                            record.key(), record.value());

                    MY_MAP.put("multiply", record.value().split("-")[0]);
                }
            }
        } finally {
            consumer.close();
        }
    }
}
