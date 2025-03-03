package com.orderms.infrastructure.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationResult {
    private final boolean valid;
    private final List<String> errors;
}