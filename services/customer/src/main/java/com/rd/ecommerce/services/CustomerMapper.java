package com.rd.ecommerce.services;


import com.rd.ecommerce.dto.CustomerRequest;
import com.rd.ecommerce.dto.CustomerResponse;
import com.rd.ecommerce.models.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            //TODO
            return null;
        }
        return Customer
                .builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress());
    }
}
