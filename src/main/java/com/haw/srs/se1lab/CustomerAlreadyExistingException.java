package com.haw.srs.se1lab;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@ResponseStatus(HttpStatus.BAD_REQUEST)
class CustomerAlreadyExistingException extends Exception {

    private final String lastName;

    CustomerAlreadyExistingException(String lastName) {
        super(String.format("Customer with name %s does already exist.", lastName));

        this.lastName = lastName;
    }
}