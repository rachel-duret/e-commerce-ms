package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.ProductPurchaseRequest;
import com.rd.ecommerce.dto.ProductPurchaseResponse;
import com.rd.ecommerce.dto.ProductRequest;
import com.rd.ecommerce.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProductById(ProductRequest productRequest);

    ProductResponse getProductById(Integer productId);

    List<ProductResponse> getAllProducts();

    void deleteProductById(Integer productId);

    Boolean existById(Integer productId);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests);
}
