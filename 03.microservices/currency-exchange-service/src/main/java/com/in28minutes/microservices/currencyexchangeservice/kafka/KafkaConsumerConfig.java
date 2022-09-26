package com.in28minutes.microservices.currencyexchangeservice.kafka;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import io.vavr.collection.Map;

public class KafkaConsumerConfig {


    @Value("localhost:9092")
    private String bootstrapServers;

    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = (Map<String, Object>) new HashMap();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }


    //creating consumer instances
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        //return new DefaultKafkaConsumerFactory<>() consumerConfig());
        return new DefaultKafkaConsumerFactory<>((java.util.Map<String, Object>) consumerConfig());

    }

    //listerner container
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(
            ConsumerFactory<String, String> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
