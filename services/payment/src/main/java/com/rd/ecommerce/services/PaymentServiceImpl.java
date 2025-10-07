package com.rd.ecommerce.services;


import com.rd.ecommerce.dto.PaymentNotificationRequest;
import com.rd.ecommerce.dto.PaymentRequest;
import com.rd.ecommerce.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Integer createPayment(PaymentRequest paymentRequest) {
//        saving the payment
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
//        sending notification message
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstname(),
                        paymentRequest.customer().lastname(),
                        paymentRequest.customer().email()
                )
        );

        return payment.getId();
    }
}
