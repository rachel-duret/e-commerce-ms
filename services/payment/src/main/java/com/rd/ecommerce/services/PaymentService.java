package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.PaymentRequest;

public interface PaymentService {
    Integer createPayment(PaymentRequest paymentRequest);
}
