package com.haw.se1lab.dataaccess.api.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.datatype.PhoneNumber;
import com.haw.se1lab.dataaccess.api.entity.Customer;

/**
 * Test class for {@link CustomerRepository}.
 * 
 * @author Arne Busch
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	public void setUp() {
		customerRepository.deleteAll();
		customerRepository.save(new Customer(new CustomerNumber(1), "Arne", "Busch", Gender.MALE,
				"arne.busch@haw-hamburg.de", new PhoneNumber("+49", "040", "12345678")));
	}

	@Test
	public void findByCustomerNumber_Success() {
		// [GIVEN]

		// [WHEN]

		// [THEN]
		Optional<Customer> customer = customerRepository.findByCustomerNumber(new CustomerNumber(1));
		assertThat(customer.isPresent()).isTrue();
	}

	@Test
	public void findByCustomerNumber_SuccessWithEmptyResult() {
		// [GIVEN]

		// [WHEN]

		// [THEN]
		Optional<Customer> customer = customerRepository.findByCustomerNumber(new CustomerNumber(9999));
		assertThat(customer.isPresent()).isFalse();
	}

	@Test
	public void findByLastName_Success() {
		// [GIVEN]

		// [WHEN]

		// [THEN]
		Optional<Customer> customer = customerRepository.findByLastName("Busch");
		assertThat(customer.isPresent()).isTrue();
	}

	@Test
	public void findByLastName_SuccessWithEmptyResult() {
		// [GIVEN]

		// [WHEN]

		// [THEN]
		Optional<Customer> customer = customerRepository.findByLastName("Not-Existing");
		assertThat(customer.isPresent()).isFalse();
	}

}
