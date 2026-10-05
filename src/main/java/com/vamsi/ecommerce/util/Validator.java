package com.vamsi.ecommerce.util;

import java.math.BigDecimal;
import com.vamsi.ecommerce.exception.ValidationException;

public class Validator {

    public static String requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(fieldName + " must not be blank");
        }
        return value;
    }

    public static BigDecimal requirePositive(
            BigDecimal value,
            String fieldName) {

        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException(fieldName + " must be positive");
        }

        return value;
    }

    public static int requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new ValidationException(fieldName + " must be non-negative");
        }

        return value;
    }

    public static int requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new ValidationException(fieldName + " must be positive");
        }

        return value;
    }
}