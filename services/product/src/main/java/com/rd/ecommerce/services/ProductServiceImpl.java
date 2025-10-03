package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.ProductPurchaseRequest;
import com.rd.ecommerce.dto.ProductPurchaseResponse;
import com.rd.ecommerce.dto.ProductRequest;
import com.rd.ecommerce.dto.ProductResponse;
import com.rd.ecommerce.exceptions.ProductNotFoundException;
import com.rd.ecommerce.exceptions.ProductPurchaseException;
import com.rd.ecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProductById(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductResponse getProductById(Integer productId) {
        return productRepository
                .findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(
                        format("No product found with ID:: %s", productId)
                ));
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductById(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(
                    format("No product found with ID:: %s", productId)
            );
        }
        productRepository.deleteById(productId);

    }

    @Override
    public Boolean existById(Integer productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
//        check if products exist
        var productIds = requests.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
//        check if products are available
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("Some of the products are not available");
        }
        var sortedRequest = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
//        check if there is enough quantity of products
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException(format("Not enough quantity of product:: %s", product.getName()));
            }
//            if there is enough quantity of products, then update the available quantity
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));

        }
        return purchasedProducts;
    }
}
