package com.rd.ecommerce.client;


import com.rd.ecommerce.dto.PurchaseRequest;
import com.rd.ecommerce.dto.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


// When interacting with downstream services or external APIs that we don't control, Better to use RestTemplate web client.
@FeignClient(
        name = "product-service",
        url = "${application.config.productUrl}"
)
public class ProductClient {
    @GetMapping("/purchase")
    List<PurchaseResponse> purchaseProducts(List<ProductPurchaseRequest>)
}
