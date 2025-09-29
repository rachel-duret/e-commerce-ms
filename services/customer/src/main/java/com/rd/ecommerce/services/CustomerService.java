package com.rd.ecommerce.services;

import com.rd.ecommerce.dto.CustomerRequest;
import com.rd.ecommerce.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest customerRequest);

    void updateCustomer(CustomerRequest customerRequest);

    void deleteCustomer(String customerId);

    List<CustomerResponse> findAllCustomers();

    Boolean existById(String customerId);

    CustomerResponse findById(String customerId);
}
