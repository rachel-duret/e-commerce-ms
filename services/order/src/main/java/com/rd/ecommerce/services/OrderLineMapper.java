package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.OrderLineRequest;
import com.rd.ecommerce.dto.OrderLineResponse;
import com.rd.ecommerce.models.Order;
import com.rd.ecommerce.models.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine
                .builder()
                .id(request.id())
                .order(
                        Order
                                .builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .productId(request.productId())
                .build();

    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
