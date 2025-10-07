package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.OrderRequest;
import com.rd.ecommerce.dto.OrderResponse;
import com.rd.ecommerce.models.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
        if (orderRequest == null) {
            // TODO
            return null;
        }
        return Order
                .builder()
                .id(orderRequest.id())
                .reference(orderRequest.reference())
                .paymentMethod(orderRequest.paymentMethod())
                .customerId(orderRequest.customerId())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
