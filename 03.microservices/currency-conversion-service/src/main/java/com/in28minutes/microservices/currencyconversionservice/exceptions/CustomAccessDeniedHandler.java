package com.in28minutes.microservices.currencyconversionservice.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    // Jackson JSON serializer instance
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {


        HttpStatus httpStatus = HttpStatus.FORBIDDEN; // 403

        Map<String, Object> data = new HashMap<>();
        data.put(
                "timestamp",
                new Date()
        );
        data.put(
                "code",
                httpStatus.value()
        );
        data.put(
                "status",
                httpStatus.name()
        );
        data.put(
                "message",
                accessDeniedException.getMessage() + "from Custom Access denied :)"
        );

        // setting the response HTTP status code
        response.setStatus(httpStatus.value());

        // serializing the response body in JSON
        response
                .getOutputStream()
                .println(
                        objectMapper.writeValueAsString(data)
                );
    }
}
