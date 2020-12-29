package com.haw.se1lab.logic.api.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
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

	@BeforeEach
	public void setUp() {
		// set up fresh test data

		customer1 = new Customer(new CustomerNumber(2), "Jane", "Doe", Gender.FEMALE, "jane.doe@haw-hamburg.de",
				new PhoneNumber("+49", "040", "88888888"));
		customerRepository.save(customer1);

		customer2 = new Customer(new CustomerNumber(3), "John", "Smith", Gender.MALE, "john.smith@haw-hamburg.de",
				new PhoneNumber("+49", "040", "99999999"));
		customerRepository.save(customer2);

		course = new Course(new CourseNumber("SE2"), "Software Engineering 2");
		courseRepository.save(course);
	}

	@AfterEach
	public void tearDown() {
		// clean up test data

		if (customer1 != null && customerRepository.findById(customer1.getId()).isPresent()) {
			customerRepository.deleteById(customer1.getId());
		}

		if (customer2 != null && customerRepository.findById(customer2.getId()).isPresent()) {
			customerRepository.deleteById(customer2.getId());
		}

		if (course != null && courseRepository.findById(course.getId()).isPresent()) {
			courseRepository.deleteById(course.getId());
		}
	}

	@Test
	public void enrollInCourse_Success() throws CustomerNotFoundException, CourseNotFoundException {
		// [GIVEN]
		String customerLastName = customer1.getLastName();
		String courseName = course.getName();

		// [WHEN]
		courseUseCase.enrollInCourse(customerLastName, courseName);

		// [THEN]
		Customer loadedCustomer = customerUseCase.findCustomerByLastName(customerLastName);
		assertThat(loadedCustomer.getCourses()).hasSize(1);
		assertThat(loadedCustomer.getCourses()).extracting(Course::getName).containsOnlyOnce(courseName);
	}

	@Test
	public void enrollInCourse_FailBecauseCustomerNotFound() {
		// [GIVEN]
		String customerLastName = "Not-Existing";
		String courseName = course.getName();

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(CustomerNotFoundException.class)
				.isThrownBy(() -> courseUseCase.enrollInCourse(customerLastName, courseName))
				.withMessageContaining(String
						.format(CustomerNotFoundException.CUSTOMER_WITH_LAST_NAME_NOT_FOUND_MESSAGE, customerLastName));
	}

	@Test
	public void transferCourses_Success() throws CustomerNotFoundException {
		// [GIVEN]
		customer1.addCourse(course);
		customerRepository.save(customer1);
		String fromCustomerLastName = customer1.getLastName();
		String toCustomerLastName = customer2.getLastName();
		String courseName = course.getName();

		// [WHEN]
		courseUseCase.transferCourses(fromCustomerLastName, toCustomerLastName);

		// [THEN]
		Customer loadedFromCustomer = customerUseCase.findCustomerByLastName(fromCustomerLastName);
		Customer loadedToCustomer = customerUseCase.findCustomerByLastName(toCustomerLastName);
		assertThat(loadedFromCustomer.getCourses()).hasSize(0);
		assertThat(loadedToCustomer.getCourses()).hasSize(1);
		assertThat(loadedToCustomer.getCourses()).extracting(Course::getName).containsOnlyOnce(courseName);
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
	public void cancelMembership_SuccessBDDStyle()
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// [GIVEN]
		customer1.addCourse(course);
		customerRepository.save(customer1);
		CustomerNumber customerNumber = customer1.getCustomerNumber();
		CourseNumber courseNumber = course.getCourseNumber();

		// configure mock for MailGateway in BDD style
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
	public void cancelMembership_FailBecauseUnableToSendMail() {
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
				.withMessageContaining("Could not send membership mail to");
	}

}
