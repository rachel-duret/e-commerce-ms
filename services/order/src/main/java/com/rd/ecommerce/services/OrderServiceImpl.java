package com.rd.ecommerce.services;


import com.rd.ecommerce.Repositories.OrderRepository;
import com.rd.ecommerce.client.CustomerClient;
import com.rd.ecommerce.client.ProductClient;
import com.rd.ecommerce.dto.OrderLineRequest;
import com.rd.ecommerce.dto.OrderRequest;
import com.rd.ecommerce.dto.OrderResponse;
import com.rd.ecommerce.dto.PurchaseRequest;
import com.rd.ecommerce.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;

    @Override
    public String createOrder(OrderRequest orderRequest) {
//        checks if the order exists
//        checks if the customer exists -- OpenFeign
        var customer = customerClient
                .findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Can not create order:: Customer not found with the provided ID"));
//        purchase the product --> product-service(RestTemplate)
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());
//        persist the order
        var order = orderRepository.save(orderMapper.toOrder(orderRequest));
//        persist order lines
        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.createOrderLine(new OrderLineRequest(
                    null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()
            ));
        }
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
