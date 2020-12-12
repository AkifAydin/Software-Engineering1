package com.haw.se1lab;

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
import com.haw.se1lab.Course;
import com.haw.se1lab.CourseNotFoundException;
import com.haw.se1lab.CourseNumber;
import com.haw.se1lab.CourseUseCase;
import com.haw.se1lab.Customer;
import com.haw.se1lab.CustomerNotFoundException;
import com.haw.se1lab.CustomerNumber;
import com.haw.se1lab.CustomerRepository;
import com.haw.se1lab.CustomerUseCase;
import com.haw.se1lab.Gender;
import com.haw.se1lab.MailUseCase;
import com.haw.se1lab.MembershipMailNotSentException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CourseUseCaseTest {

	@Autowired
	private CustomerUseCase customerUseCase;

	@Autowired
	private CourseUseCase courseUseCase;

	@Autowired
	private CustomerRepository customerRepository;

	@MockBean
	private MailUseCase mailGateway;

	@BeforeEach
	void setup() {
		customerRepository.deleteAll();
	}

	@Test
	public void enrollInCourse_Success() throws CustomerNotFoundException {
		// [GIVEN]
		Customer customer = new Customer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
		customerRepository.save(customer);

		// [WHEN]
		courseUseCase.enrollInCourse(customer.getLastName(), new Course("Software Engineering 1"));

		// [THEN]
		Customer loadedCustomer = customerUseCase.findCustomerByLastName(customer.getLastName());
		assertThat(loadedCustomer.getCourses()).size().isEqualTo(1);
	}

	@Test
	public void enrollInCourse_FailBecauseCustomerNotFound() {
		// [GIVEN]

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(CustomerNotFoundException.class)
				.isThrownBy(() -> courseUseCase.enrollInCourse("Not-Existing", new Course("Software Engineering 1")))
				.withMessageContaining(String
						.format(CustomerNotFoundException.CUSTOMER_WITH_LAST_NAME_NOT_FOUND_MESSAGE, "Not-Existing"));
	}

	@Test
	public void transferCourses_Success() throws CustomerNotFoundException {
		// [GIVEN]
		Customer from = new Customer("John", "Smith", Gender.MALE);
		from.addCourse(new Course("Software Engineering 1"));
		from.addCourse(new Course("Software Engineering 2"));
		customerRepository.save(from);

		Customer to = new Customer("Eva", "Miller", Gender.FEMALE);
		customerRepository.save(to);

		// [WHEN]
		courseUseCase.transferCourses(from.getLastName(), to.getLastName());

		// [THEN]
		assertThat(customerUseCase.findCustomerByLastName(from.getLastName()).getCourses()).size().isEqualTo(0);
		assertThat(customerUseCase.findCustomerByLastName(to.getLastName()).getCourses()).size().isEqualTo(2);
	}

	@Test
	public void cancelMembership_Success()
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// [GIVEN]
		// set up customer and course here
		// ...

		// configure mock for MailGateway
		when(mailGateway.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

		// [WHEN]
		courseUseCase.cancelMembership(new CustomerNumber(1L), new CourseNumber(1L));

		// [THEN]
		// check that MailGateway was called
		// ...
	}

	@Test
	public void cancelMembership_SuccessBDDStyle()
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// [GIVEN]
		// set up customer and course here
		// ...

		// configure MailGateway-mock with BDD-style
		given(mailGateway.sendMail(anyString(), anyString(), anyString())).willReturn(true);

		// [WHEN]
		courseUseCase.cancelMembership(new CustomerNumber(1L), new CourseNumber(1L));

		// [THEN]
		// check that MailGateway was called
		// ...
	}

	@Test
	public void cancelMembership_FailBecauseUnableToSendMail() {
		// [GIVEN]
		// set up customer and course here
		// ...

		// configure MailGateway-mock
		when(mailGateway.sendMail(anyString(), anyString(), anyString())).thenReturn(false);

		// [WHEN]
		// [THEN]
		assertThatExceptionOfType(MembershipMailNotSentException.class)
				.isThrownBy(() -> courseUseCase.cancelMembership(new CustomerNumber(1L), new CourseNumber(1L)))
				.withMessageContaining("Could not send membership mail to");
	}

}
