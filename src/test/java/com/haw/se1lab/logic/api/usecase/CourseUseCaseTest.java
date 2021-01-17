package com.haw.se1lab.logic.api.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;
import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.datatype.PhoneNumber;
import com.haw.se1lab.common.api.exception.CourseNotFoundException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.common.api.exception.MembershipMailNotSentException;
import com.haw.se1lab.dataaccess.api.entity.Course;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CourseRepository;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;

/**
 * Test class for {@link CourseUseCase}.
 * 
 * @author Arne Busch
 */
@ActiveProfiles("test") // causes exclusive creation of general and test-specific beans (marked by @Profile("test"))
@ExtendWith(SpringExtension.class) // required to use Spring TestContext Framework in JUnit 5
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE) // test environment
public class CourseUseCaseTest {

	@Autowired
	private CourseUseCase courseUseCase;

	@Autowired
	private CustomerUseCase customerUseCase;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CourseRepository courseRepository;

	@MockBean
	private MailUseCase mailUseCase;

	private Course course;

	private Customer customer1;

	private Customer customer2;

	@BeforeAll
	public static void setUpAll() {
		// actions to be performed once before execution of first test method

	}

	@AfterAll
	public static void tearDownAll() {
		// actions to be performed once after execution of last test method

	}

	@BeforeEach
	public void setUp() {
		// set up fresh test data before each test method execution

		course = new Course(new CourseNumber("SE2"), "Software Engineering 2");
		courseRepository.save(course);

		customer1 = new Customer(new CustomerNumber(2), "Jane", "Doe", Gender.FEMALE, "jane.doe@haw-hamburg.de",
				new PhoneNumber("+49", "040", "88888888"));
		customerRepository.save(customer1);

		customer2 = new Customer(new CustomerNumber(3), "John", "Smith", Gender.MALE, "john.smith@haw-hamburg.de",
				new PhoneNumber("+49", "040", "99999999"));
		customerRepository.save(customer2);
	}

	@AfterEach
	public void tearDown() {
		// clean up test data after each test method execution

		customerRepository.deleteAll(); // must be done first -> integrity constraints (Customer references Course)
		courseRepository.deleteAll();
	}

	@Test
	public void enrollInCourse_Success() throws CustomerNotFoundException, CourseNotFoundException {
		// [GIVEN]
		CustomerNumber customerNumber = customer1.getCustomerNumber();
		CourseNumber courseNumber = course.getCourseNumber();

		// [WHEN]
		courseUseCase.enrollInCourse(customerNumber, courseNumber);

		// [THEN]
		Customer loadedCustomer = customerUseCase.findCustomerByCustomerNumber(customerNumber);
		assertThat(loadedCustomer.getCourses()).hasSize(1);
		assertThat(loadedCustomer.getCourses()).extracting(Course::getCourseNumber).containsOnlyOnce(courseNumber);
	}

	@Test
	public void enrollInCourse_Fail_CustomerNumberNull() {
		// [GIVEN]
		CustomerNumber customerNumber = null;
		CourseNumber courseNumber = course.getCourseNumber();

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> courseUseCase.enrollInCourse(customerNumber, courseNumber));
	}

	@Test
	public void enrollInCourse_Fail_CourseNumberNull() {
		// [GIVEN]
		CustomerNumber customerNumber = customer1.getCustomerNumber();
		CourseNumber courseNumber = null;

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> courseUseCase.enrollInCourse(customerNumber, courseNumber));
	}

	@Test
	public void enrollInCourse_Fail_CustomerNotFound() {
		// [GIVEN]
		CustomerNumber customerNumber = new CustomerNumber(9999);
		CourseNumber courseNumber = course.getCourseNumber();

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(CustomerNotFoundException.class)
				.isThrownBy(() -> courseUseCase.enrollInCourse(customerNumber, courseNumber)).withMessageContaining(
						String.format(CustomerNotFoundException.CUSTOMER_WITH_CUSTOMER_NUMBER_NOT_FOUND_MESSAGE,
								customerNumber.getNumber()));
	}

	@Test
	public void enrollInCourse_Fail_CourseNotFound() {
		// [GIVEN]
		CustomerNumber customerNumber = customer1.getCustomerNumber();
		CourseNumber courseNumber = new CourseNumber("0000");

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(CourseNotFoundException.class)
				.isThrownBy(() -> courseUseCase.enrollInCourse(customerNumber, courseNumber)).withMessageContaining(
						String.format(CourseNotFoundException.COURSE_WITH_COURSE_NUMBER_NOT_FOUND_MESSAGE,
								courseNumber.getCode()));
	}

	@Test
	public void transferCourses_Success() throws CustomerNotFoundException {
		// [GIVEN]
		customer1.addCourse(course);
		customerRepository.save(customer1);
		CustomerNumber fromCustomerNumber = customer1.getCustomerNumber();
		CustomerNumber toCustomerNumber = customer2.getCustomerNumber();
		String courseName = course.getName();

		// [WHEN]
		courseUseCase.transferCourses(fromCustomerNumber, toCustomerNumber);

		// [THEN]
		Customer loadedFromCustomer = customerUseCase.findCustomerByCustomerNumber(fromCustomerNumber);
		Customer loadedToCustomer = customerUseCase.findCustomerByCustomerNumber(toCustomerNumber);
		assertThat(loadedFromCustomer.getCourses()).hasSize(0);
		assertThat(loadedToCustomer.getCourses()).hasSize(1);
		assertThat(loadedToCustomer.getCourses()).extracting(Course::getName).containsOnlyOnce(courseName);
	}

	@Test
	public void transferCourses_Fail_FromCustomerNumberNull() {
		// [GIVEN]
		CustomerNumber fromCustomerNumber = null;
		CustomerNumber toCustomerNumber = customer2.getCustomerNumber();

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> courseUseCase.transferCourses(fromCustomerNumber, toCustomerNumber));
	}

	@Test
	public void transferCourses_Fail_ToCustomerNumberNull() {
		// [GIVEN]
		CustomerNumber fromCustomerNumber = customer1.getCustomerNumber();
		CustomerNumber toCustomerNumber = null;

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> courseUseCase.transferCourses(fromCustomerNumber, toCustomerNumber));
	}

	@Test
	public void transferCourses_Fail_FromCustomerNotFound() {
		// [GIVEN]
		CustomerNumber fromCustomerNumber = new CustomerNumber(9999);
		CustomerNumber toCustomerNumber = customer2.getCustomerNumber();

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(CustomerNotFoundException.class)
				.isThrownBy(() -> courseUseCase.transferCourses(fromCustomerNumber, toCustomerNumber))
				.withMessageContaining(
						String.format(CustomerNotFoundException.CUSTOMER_WITH_CUSTOMER_NUMBER_NOT_FOUND_MESSAGE,
								fromCustomerNumber.getNumber()));
	}

	@Test
	public void transferCourses_Fail_ToCustomerNotFound() {
		// [GIVEN]
		CustomerNumber fromCustomerNumber = customer1.getCustomerNumber();
		CustomerNumber toCustomerNumber = new CustomerNumber(9999);

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(CustomerNotFoundException.class)
				.isThrownBy(() -> courseUseCase.transferCourses(fromCustomerNumber, toCustomerNumber))
				.withMessageContaining(
						String.format(CustomerNotFoundException.CUSTOMER_WITH_CUSTOMER_NUMBER_NOT_FOUND_MESSAGE,
								toCustomerNumber.getNumber()));
	}

	@Test
	public void cancelMembership_Success()
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// [GIVEN]
		customer1.addCourse(course);
		customerRepository.save(customer1);
		CustomerNumber customerNumber = customer1.getCustomerNumber();
		CourseNumber courseNumber = course.getCourseNumber();

		// configure mock for MailGateway
		when(mailUseCase.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

		// [WHEN]
		courseUseCase.cancelMembership(customerNumber, courseNumber);

		// [THEN]
		Customer loadedCustomer = customerRepository.findByCustomerNumber(customerNumber).get();
		assertThat(loadedCustomer.getCourses()).hasSize(0);

		// check that MailGateway was called
		// ...
	}

	@Test
	public void cancelMembership_Success_BDDStyle()
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// [GIVEN]
		customer1.addCourse(course);
		customerRepository.save(customer1);
		CustomerNumber customerNumber = customer1.getCustomerNumber();
		CourseNumber courseNumber = course.getCourseNumber();

		// configure mock for MailGateway in Behavior Driven Development (BDD) style
		given(mailUseCase.sendMail(anyString(), anyString(), anyString())).willReturn(true);

		// [WHEN]
		courseUseCase.cancelMembership(customerNumber, courseNumber);

		// [THEN]
		Customer loadedCustomer = customerRepository.findByCustomerNumber(customerNumber).get();
		assertThat(loadedCustomer.getCourses()).hasSize(0);

		// check that MailGateway was called
		// ...
	}

	@Test
	public void cancelMembership_Fail_CustomerNumberNull() {
		// [GIVEN]
		CustomerNumber customerNumber = null;
		CourseNumber courseNumber = course.getCourseNumber();

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> courseUseCase.cancelMembership(customerNumber, courseNumber));
	}

	@Test
	public void cancelMembership_Fail_CourseNumberNull() {
		// [GIVEN]
		CustomerNumber customerNumber = customer1.getCustomerNumber();
		CourseNumber courseNumber = null;

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> courseUseCase.cancelMembership(customerNumber, courseNumber));
	}

	@Test
	public void cancelMembership_Fail_MailNotSent() {
		// [GIVEN]
		customer1.addCourse(course);
		customerRepository.save(customer1);
		CustomerNumber customerNumber = customer1.getCustomerNumber();
		CourseNumber courseNumber = course.getCourseNumber();

		// configure mock for MailGateway
		when(mailUseCase.sendMail(anyString(), anyString(), anyString())).thenReturn(false);

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(MembershipMailNotSentException.class)
				.isThrownBy(() -> courseUseCase.cancelMembership(customerNumber, courseNumber))
				.withMessageContaining(String.format(MembershipMailNotSentException.COULD_NOT_SEND_MEMBERSHIP_MAIL,
						customer1.getEmail()));
	}

}
