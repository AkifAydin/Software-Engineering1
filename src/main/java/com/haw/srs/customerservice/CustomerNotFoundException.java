package com.haw.srs.customerservice;

import lombok.Value;

@Value
class CustomerNotFoundException extends Exception {

    private final Long customerId;

    public CustomerNotFoundException(Long customerId) {
        super(String.format("Could not find customer with numer %d.", customerId));

        this.customerId = customerId;
    }
}