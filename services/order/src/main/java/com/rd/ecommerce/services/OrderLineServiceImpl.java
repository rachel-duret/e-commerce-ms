package com.rd.ecommerce.services;

import com.rd.ecommerce.Repositories.OrderLineRepository;
import com.rd.ecommerce.dto.OrderLineRequest;
import com.rd.ecommerce.dto.OrderLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {
    private final OrderLineMapper orderLineMapper;
    private final OrderLineRepository orderLineRepository;

    @Override
    public Integer createOrderLine(OrderLineRequest request) {
        return orderLineRepository.save(orderLineMapper.toOrderLine(request)).getId();
    }

    @Override
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
