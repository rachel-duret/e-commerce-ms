package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.OrderRequest;
import com.rd.ecommerce.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    Integer createOrder(OrderRequest orderRequest);

    List<OrderResponse> findAllOrders();

    OrderResponse findOrderById(Integer orderId);
}
