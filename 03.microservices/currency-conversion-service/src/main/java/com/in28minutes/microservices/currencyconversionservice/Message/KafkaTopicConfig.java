package com.in28minutes.microservices.currencyconversionservice.Message;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic xecTopic(){
        return TopicBuilder.name("xecTest").build();
    }

    @Bean
    public NewTopic currencyConversion(){
        return TopicBuilder.name("currencyConversion").build();
    }
}
