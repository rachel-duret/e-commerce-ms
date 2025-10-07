package com.rd.ecommerce.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record PurchaseRequest(
        @NotNull(message = "Product id is mandatory")
        Integer productId,
        @Positive(message = "Quantity should be positive")
        double quantity
) {
}
