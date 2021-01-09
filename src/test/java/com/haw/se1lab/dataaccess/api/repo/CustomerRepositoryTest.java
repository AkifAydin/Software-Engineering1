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
import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.datatype.PhoneNumber;
import com.haw.se1lab.dataaccess.api.entity.Course;
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

	@Autowired
	private CourseRepository courseRepository;

	private Customer customer1;

	private Customer customer2;

	private Customer customer3;

	private Course course;

	@BeforeAll
	public static void setUpAll() {
		// actions to be performed once before execution of first test method

		// consider initial data created by Application.InitialDataInsertionRunner
		numberOfInitiallyAvailableCustomers = 1;
	}

	@BeforeEach
	public void setUp() {
		// set up fresh test data before each test method execution

		course = new Course(new CourseNumber("SE2"), "Software Engineering 2");
		courseRepository.save(course);

		customer1 = new Customer(new CustomerNumber(2), "Jane", "Doe", Gender.FEMALE, "jane.doe@haw-hamburg.de",
				new PhoneNumber("+49", "040", "11111111"));
		customer1.addCourse(course);
		customerRepository.save(customer1);

		customer2 = new Customer(new CustomerNumber(3), "Jane", "Doe", Gender.OTHER, "jane.doe@uni-hamburg.de",
				new PhoneNumber("+49", "040", "22222222"));
		customer2.addCourse(course);
		customerRepository.save(customer2);

		customer3 = new Customer(new CustomerNumber(4), "Jane", "Doe", Gender.UNKNOWN, null,
				new PhoneNumber("+49", "040", "33333333"));
		customer3.addCourse(course);
		customerRepository.save(customer3);
	}

	@AfterEach
	public void tearDown() {
		// clean up test data after each test method execution

		if (customer1 != null && customerRepository.findById(customer1.getId()).isPresent()) {
			customerRepository.deleteById(customer1.getId());
		}

		if (customer2 != null && customerRepository.findById(customer2.getId()).isPresent()) {
			customerRepository.deleteById(customer2.getId());
		}

		if (customer3 != null && customerRepository.findById(customer3.getId()).isPresent()) {
			customerRepository.deleteById(customer3.getId());
		}

		if (course != null && courseRepository.findById(course.getId()).isPresent()) {
			courseRepository.deleteById(course.getId());
		}
	}

	@Test
	public void findByCustomerNumber_Success() {
		// [GIVEN]
		CustomerNumber customerNumber = customer1.getCustomerNumber();

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
	public void findByFirstNameAndLastNameAndCoursesContainingAndEmailNotNullOrderByGenderDesc_Success() {
		// [GIVEN]
		String firstName = customer1.getFirstName();
		String lastName = customer1.getLastName();
		Course course = customer1.getCourses().get(0);
		CustomerNumber customerNumber1 = customer1.getCustomerNumber();
		CustomerNumber customerNumber2 = customer2.getCustomerNumber();

		// [WHEN]
		List<Customer> loadedCustomers = customerRepository
				.findByFirstNameAndLastNameAndCoursesContainingAndEmailNotNullOrderByGenderDesc(firstName, lastName,
						course);

		// [THEN]
		assertThat(loadedCustomers).hasSize(2);
		assertThat(loadedCustomers.get(0).getCustomerNumber()).isEqualTo(customerNumber2);
		assertThat(loadedCustomers.get(1).getCustomerNumber()).isEqualTo(customerNumber1);
	}

	@Test
	public void deleteByCustomerNumber_Success() {
		// [GIVEN]
		CustomerNumber customerNumber = customer1.getCustomerNumber();

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
		assertThat(loadedCustomers).hasSize(numberOfInitiallyAvailableCustomers + 3); // take initial data into account
	}

}
