package com.in28minutes.microservices.currencyconversionservice.Message.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest{

    @JsonProperty("message")
   private String message;

    public MessageRequest(String message) {
        this.message = message;
    }

    public MessageRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
