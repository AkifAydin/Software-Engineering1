package com.haw.se1lab.dataaccess.api.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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

	private static int numberOfInitiallyAvailableCustomers;

	@Autowired
	private CustomerRepository customerRepository;

	private Customer customer;

	@BeforeAll
	public static void setUpAll() {
		// actions to be performed once before execution of first test method

		// consider initial data created by Application.InitialDataInsertionRunner
		numberOfInitiallyAvailableCustomers = 1;
	}

	@BeforeEach
	public void setUp() {
		// set up fresh test data before each test method execution

		customer = new Customer(new CustomerNumber(2), "Jane", "Doe", Gender.FEMALE, "jane.doe@haw-hamburg.de",
				new PhoneNumber("+49", "040", "88888888"));
		customerRepository.save(customer);
	}

	@AfterEach
	public void tearDown() {
		// clean up test data after each test method execution

		if (customer != null && customerRepository.findById(customer.getId()).isPresent()) {
			customerRepository.deleteById(customer.getId());
		}
	}

	@Test
	public void findByCustomerNumber_Success() {
		// [GIVEN]
		CustomerNumber customerNumber = customer.getCustomerNumber();

		// [WHEN]
		Optional<Customer> loadedCustomer = customerRepository.findByCustomerNumber(customerNumber);

		// [THEN]
		assertThat(loadedCustomer.isPresent()).isTrue();
		assertThat(loadedCustomer.get().getCustomerNumber()).isEqualTo(customerNumber);
	}

	@Test
	public void findByCustomerNumber_SuccessWithEmptyResult() {
		// [GIVEN]
		CustomerNumber customerNumber = new CustomerNumber(9999);

		// [WHEN]
		Optional<Customer> loadedCustomer = customerRepository.findByCustomerNumber(customerNumber);

		// [THEN]
		assertThat(loadedCustomer.isPresent()).isFalse();
	}

	@Test
	public void deleteByCustomerNumber_Success() {
		// [GIVEN]
		CustomerNumber customerNumber = customer.getCustomerNumber();

		// [WHEN]
		customerRepository.deleteByCustomerNumber(customerNumber);

		// [THEN]
		Optional<Customer> loadedCustomer = customerRepository.findByCustomerNumber(customerNumber);
		assertThat(loadedCustomer.isPresent()).isFalse();
	}

	@Test
	public void deleteByCustomerNumber_SuccessWithNoActualDeletion() {
		// [GIVEN]
		CustomerNumber customerNumber = new CustomerNumber(9999);

		// [WHEN]
		customerRepository.deleteByCustomerNumber(customerNumber);

		// [THEN]
		List<Customer> loadedCustomers = customerRepository.findAll();
		assertThat(loadedCustomers).hasSize(numberOfInitiallyAvailableCustomers + 1); // take initial data into account
	}

}
