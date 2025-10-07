package com.rd.ecommerce.client;


import com.rd.ecommerce.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customerUrl}"
)

public interface CustomerClient {
    @GetMapping("/{customerId}")
    Optional<CustomerResponse> findCustomerById(@PathVariable String customerId);
}
