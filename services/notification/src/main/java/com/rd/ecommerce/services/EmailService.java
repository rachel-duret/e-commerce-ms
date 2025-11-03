package com.rd.ecommerce.services;


import com.rd.ecommerce.dto.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rd.ecommerce.models.EmailTemplates.ORDER_CONFIRMATION;
import static com.rd.ecommerce.models.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        //TODO to set the mail
        mimeMessageHelper.setFrom("contact@localhost");

        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variable = new HashMap<>();
        variable.put("customerName", customerName);
        variable.put("amount", amount);
        variable.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variable);
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            mimeMessageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
        } catch (MessagingException exception) {
            log.warn("WARN: Email could not be sent to {}. Exception: {}", destinationEmail, exception.getMessage());
        }

    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        // TODO to set the mail
        mimeMessageHelper.setFrom("contact@localhost");

        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variable = new HashMap<>();
        variable.put("customerName", customerName);
        variable.put("totalAmount", amount);
        variable.put("orderReference", orderReference);
        variable.put("products", products);

        Context context = new Context();
        context.setVariables(variable);
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            mimeMessageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
        } catch (MessagingException exception) {
            log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
        }
    }
}
