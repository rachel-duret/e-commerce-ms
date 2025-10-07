package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.OrderLineRequest;
import com.rd.ecommerce.dto.OrderLineResponse;

import java.util.List;

public interface OrderLineService {
    Integer createOrderLine(OrderLineRequest request);

    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}
