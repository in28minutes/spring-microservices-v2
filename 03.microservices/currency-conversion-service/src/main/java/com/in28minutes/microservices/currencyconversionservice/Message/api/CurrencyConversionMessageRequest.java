package com.in28minutes.microservices.currencyconversionservice.Message.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyConversionMessageRequest {


    @JsonProperty("requestFrom")
    private String requestFrom;

    @JsonProperty("requestTo")
    private String requestTo;

    public CurrencyConversionMessageRequest(String requestFrom,String requestTo) {
        this.requestFrom = requestFrom;
        this.requestTo = requestTo;
    }

    public CurrencyConversionMessageRequest() {
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }
}
