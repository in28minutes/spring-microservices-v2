package com.in28minutes.microservices.currencyconversionservice.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtManualSecretKey {

    private final JwtManualConfig jwtManualConfig;

    @Autowired
    public JwtManualSecretKey(JwtManualConfig jwtManualConfig) {
        this.jwtManualConfig = jwtManualConfig;
    }

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtManualConfig.getSecretKey().getBytes());
    }
}
