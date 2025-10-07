package com.rd.ecommerce.services;


import com.rd.ecommerce.dto.PaymentRequest;
import com.rd.ecommerce.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Integer createPayment(PaymentRequest paymentRequest) {
//        saving the payment
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
//        sending notification message

        return
    }
}
