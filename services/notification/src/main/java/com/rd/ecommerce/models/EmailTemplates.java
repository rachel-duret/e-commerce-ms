package com.rd.ecommerce.models;

import lombok.Getter;

public enum EmailTemplates {
    ORDER_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
    PAYMENT_CONFIRMATION("order-confirmation.html", "Order confirmation");

    @Getter
    private final String subject;
    @Getter
    private final String template;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
