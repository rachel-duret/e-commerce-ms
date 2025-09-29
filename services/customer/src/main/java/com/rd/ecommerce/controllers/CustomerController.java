package com.rd.ecommerce.controllers;


import com.rd.ecommerce.dto.CustomerRequest;
import com.rd.ecommerce.dto.CustomerResponse;
import com.rd.ecommerce.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping
    @RequestMapping("/exits/{customerId}")
    public ResponseEntity<Boolean> exitsByCustomerById(@PathVariable String customerId) {

        return ResponseEntity.ok(customerService.existById(customerId));
    }

    @GetMapping
    @RequestMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        // TODO: 10/10/2021 implement should implement return customer.
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();

    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
