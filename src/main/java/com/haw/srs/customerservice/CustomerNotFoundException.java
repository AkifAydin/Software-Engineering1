package com.haw.srs.customerservice;

import lombok.Value;

@Value
class CustomerNotFoundException extends Exception {

    private final Long customerId;
    private final String lastName;

    CustomerNotFoundException(Long customerId) {
        super(String.format("Could not find customer with numer %d.", customerId));

        this.customerId = customerId;
        this.lastName = "";
    }

    CustomerNotFoundException(String lastName) {
        super(String.format("Could not find customer with lastname %s.", lastName));

        this.customerId = 0L;
        this.lastName = lastName;
    }
}