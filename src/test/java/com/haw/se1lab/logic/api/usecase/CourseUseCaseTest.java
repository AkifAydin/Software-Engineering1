package com.haw.se1lab.logic.api.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;
import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
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
@ExtendWith(SpringExtension.class)
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

	@BeforeEach
	void setUp() {
		customerRepository.deleteAll();
	}

	@Test
	public void enrollInCourse_Success() throws CustomerNotFoundException, CourseNotFoundException {
		// [GIVEN]
		Customer customer = new Customer(new CustomerNumber(1000), "Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com",
				null);
		customerRepository.save(customer);

		String courseName = "Software Engineering 1";

		// [WHEN]
		courseUseCase.enrollInCourse(customer.getLastName(), courseName);

		// [THEN]
		customer = customerUseCase.findCustomerByLastName(customer.getLastName());
		assertThat(customer.getCourses()).size().isEqualTo(1);
		assertThat(customer.getCourses().get(0).getName()).isEqualTo(courseName);
	}

	@Test
	public void enrollInCourse_FailBecauseCustomerNotFound() {
		// [GIVEN]
		String customerLastName = "Not-Existing";
		String courseName = "Software Engineering 1";

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
		Course course = courseRepository.findByName("Software Engineering 1").get();

		Customer fromCustomer = new Customer(new CustomerNumber(2000), "John", "Smith", Gender.MALE);
		fromCustomer.addCourse(course);
		customerRepository.save(fromCustomer);

		Customer toCustomer = new Customer(new CustomerNumber(3000), "Eva", "Miller", Gender.FEMALE);
		customerRepository.save(toCustomer);

		// [WHEN]
		courseUseCase.transferCourses(fromCustomer.getLastName(), toCustomer.getLastName());

		// [THEN]
		fromCustomer = customerUseCase.findCustomerByLastName(fromCustomer.getLastName());
		toCustomer = customerUseCase.findCustomerByLastName(toCustomer.getLastName());
		assertThat(fromCustomer.getCourses()).size().isEqualTo(0);
		assertThat(toCustomer.getCourses()).size().isEqualTo(1);
		assertThat(toCustomer.getCourses().get(0).getName()).isEqualTo(course.getName());
	}

	@Test
	public void cancelMembership_Success()
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// [GIVEN]
		Course course = courseRepository.findByCourseNumber(new CourseNumber("SE1")).get();

		Customer customer = new Customer(new CustomerNumber(2000), "John", "Smith", Gender.MALE);
		customer.addCourse(course);
		customerRepository.save(customer);

		// configure mock for MailGateway
		when(mailUseCase.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

		// [WHEN]
		courseUseCase.cancelMembership(new CustomerNumber(2000), new CourseNumber("SE1"));

		// [THEN]
		customer = customerUseCase.findCustomerByLastName(customer.getLastName());
		assertThat(customer.getCourses()).size().isEqualTo(0);

		// check that MailGateway was called
		// ...
	}

	@Test
	public void cancelMembership_SuccessBDDStyle()
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// [GIVEN]
		Course course = courseRepository.findByCourseNumber(new CourseNumber("SE1")).get();

		Customer customer = new Customer(new CustomerNumber(2000), "John", "Smith", Gender.MALE);
		customer.addCourse(course);
		customerRepository.save(customer);

		// configure mock for MailGateway in BDD style
		given(mailUseCase.sendMail(anyString(), anyString(), anyString())).willReturn(true);

		// [WHEN]
		courseUseCase.cancelMembership(new CustomerNumber(2000), new CourseNumber("SE1"));

		// [THEN]
		customer = customerUseCase.findCustomerByLastName(customer.getLastName());
		assertThat(customer.getCourses()).size().isEqualTo(0);

		// check that MailGateway was called
		// ...
	}

	@Test
	public void cancelMembership_FailBecauseUnableToSendMail() {
		// [GIVEN]
		Course course = courseRepository.findByCourseNumber(new CourseNumber("SE1")).get();

		Customer customer = new Customer(new CustomerNumber(2000), "John", "Smith", Gender.MALE);
		customer.addCourse(course);
		customerRepository.save(customer);

		// configure mock for MailGateway
		when(mailUseCase.sendMail(anyString(), anyString(), anyString())).thenReturn(false);

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(MembershipMailNotSentException.class)
				.isThrownBy(() -> courseUseCase.cancelMembership(new CustomerNumber(2000), new CourseNumber("SE1")))
				.withMessageContaining("Could not send membership mail to");
	}

}
