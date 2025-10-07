package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendNotification(PaymentNotificationRequest request) {
        log.info("Sending notification with body =<{}>", request);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(message);

    }
}
