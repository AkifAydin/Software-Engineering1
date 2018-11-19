package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        this.customerRepository.deleteAll();

        Customer customer = this.customerRepository.save(new Customer("Stefan", "Sarstedt", Gender.MALE,
                "stefan.sarstedt@haw-hamburg.de",
                new PhoneNumber("+49", "040", "428758434")));
    }

    @Test
    void findCustomerByLastNameSuccess() {
        assertThat(customerRepository.findByLastName("Sarstedt").isPresent()).isTrue();
    }

    @Test
    void findCustomerByLastNameFail() {
        assertThat(customerRepository.findByLastName("notExisting").isPresent()).isFalse();
    }
}
