package com.rd.ecommerce.services;


import com.rd.ecommerce.client.CustomerClient;
import com.rd.ecommerce.client.ProductClient;
import com.rd.ecommerce.dto.OrderRequest;
import com.rd.ecommerce.dto.OrderResponse;
import com.rd.ecommerce.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    @Override
    public String createOrder(OrderRequest orderRequest) {
//        checks if the order exists
//        checks if the customer exists -- OpenFeign
        var customer = customerClient
                .findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Can not create order:: Customer not found with the provided ID"));
//        purchase the product --> product-service(RestTemplate)
//        persist the order
//        persist order lines
//        start the payment process --> payment-service TODO
//        send the order confirmation --> notification-service (kafka) TODO
        return "";
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return List.of();
    }

    @Override
    public OrderResponse findOrderById() {
        return null;
    }
}
