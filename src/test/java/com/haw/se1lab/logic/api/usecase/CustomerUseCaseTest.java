package com.haw.se1lab.logic.api.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
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
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;

/**
 * Test class for {@link CustomerUseCase}.
 * 
 * @author Arne Busch
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerUseCaseTest {

	@Autowired
	private CustomerUseCase customerUseCase;

	@Autowired
	private CustomerRepository customerRepository;

	private Customer customer;

	@BeforeEach
	public void setUp() {
		// set up fresh test data

		customer = new Customer(new CustomerNumber(2), "Jane", "Doe", Gender.FEMALE, "jane.doe@haw-hamburg.de",
				new PhoneNumber("+49", "040", "88888888"));
		customerRepository.save(customer);
	}

	@AfterEach
	public void tearDown() {
		// clean up test data

		if (customer != null && customerRepository.findById(customer.getId()).isPresent()) {
			customerRepository.deleteById(customer.getId());
		}
	}

	@Test
	public void findAllCustomers_Success() {
		// [GIVEN]
		CustomerNumber customerNumber = customer.getCustomerNumber();

		// [WHEN]
		List<Customer> loadedCustomers = customerUseCase.findAllCustomers();

		// [THEN]
		assertThat(loadedCustomers).hasSize(2); // take initial data into account
		assertThat(loadedCustomers).extracting(Customer::getCustomerNumber).containsOnlyOnce(customerNumber);
	}

	// TODO Add test methods for yet untested methods

}
