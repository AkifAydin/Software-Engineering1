package com.haw.srs.se1lab;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	public void setUp() {
		customerRepository.deleteAll();

		customerRepository.save(new Customer("Arne", "Busch", Gender.MALE, "arne.busch@haw-hamburg.de",
				new PhoneNumber("+49", "040", "12345678")));
	}

	@Test
	public void findCustomerByLastNameSuccess() {
		// [GIVEN]

		// [WHEN]

		// [THEN]
		Optional<Customer> customer = customerRepository.findByLastName("Busch");
		assertThat(customer.isPresent()).isTrue();
	}

	@Test
	public void findCustomerByLastNameFail() {
		// [GIVEN]

		// [WHEN]

		// [THEN]
		Optional<Customer> customer = customerRepository.findByLastName("Not-Existing");
		assertThat(customer.isPresent()).isFalse();
	}

}
