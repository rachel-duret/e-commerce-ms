package com.rd.ecommerce.dto;

import com.rd.ecommerce.models.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
