package com.rd.ecommerce.dto;

import com.rd.ecommerce.models.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod PaymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
