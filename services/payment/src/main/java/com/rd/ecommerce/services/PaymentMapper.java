package com.rd.ecommerce.services;


import com.rd.ecommerce.dto.PaymentRequest;
import com.rd.ecommerce.models.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest paymentRequest) {
        if (paymentRequest == null) {
//            TODO maybe create custom exception
            throw new IllegalArgumentException("Payment request cannot be null");
        }
        return Payment
                .builder()
                .id(paymentRequest.id())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .orderId(paymentRequest.orderId())
                .build();
    }
}
