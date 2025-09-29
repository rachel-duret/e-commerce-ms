package com.rd.ecommerce.repositories;

import com.rd.ecommerce.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
