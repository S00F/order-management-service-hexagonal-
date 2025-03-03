package com.orderms.infrastructure.validator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderValidationException extends RuntimeException {
    private final List<String> errors;

    public OrderValidationException(List<String> errors) {
        super("Order validation failed: " + String.join(", ", errors));
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}