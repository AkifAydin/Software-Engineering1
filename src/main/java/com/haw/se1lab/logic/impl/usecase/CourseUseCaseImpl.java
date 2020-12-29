package com.haw.se1lab.logic.impl.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.exception.CourseNotFoundException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.common.api.exception.MembershipMailNotSentException;
import com.haw.se1lab.dataaccess.api.entity.Course;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CourseRepository;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;
import com.haw.se1lab.logic.api.usecase.CourseUseCase;
import com.haw.se1lab.logic.api.usecase.MailUseCase;

/**
 * Default implementation for {@link CourseUseCase}.
 * 
 * @author Arne Busch
 */
@Service
public class CourseUseCaseImpl implements CourseUseCase {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private MailUseCase mailUseCase;

	@Override
	public void enrollInCourse(String lastName, String courseName)
			throws CustomerNotFoundException, CourseNotFoundException {
		Customer customer = customerRepository.findByLastName(lastName)
				.orElseThrow(() -> new CustomerNotFoundException(lastName));
		Course course = courseRepository.findByName(courseName)
				.orElseThrow(() -> new CourseNotFoundException(courseName));
		customer.addCourse(course);
		customerRepository.save(customer);
	}

	@Override
	public void transferCourses(String fromCustomerLastName, String toCustomerLastName)
			throws CustomerNotFoundException {
		Customer fromCustomer = customerRepository.findByLastName(fromCustomerLastName)
				.orElseThrow(() -> new CustomerNotFoundException(fromCustomerLastName));
		Customer toCustomer = customerRepository.findByLastName(toCustomerLastName)
				.orElseThrow(() -> new CustomerNotFoundException(toCustomerLastName));

		toCustomer.getCourses().addAll(fromCustomer.getCourses());
		fromCustomer.getCourses().clear();

		customerRepository.save(fromCustomer);
		customerRepository.save(toCustomer);
	}

	@Override
	public void cancelMembership(CustomerNumber customerNumber, CourseNumber courseNumber)
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		Customer customer = customerRepository.findByCustomerNumber(customerNumber)
				.orElseThrow(() -> new CustomerNotFoundException(customerNumber));
		Course course = courseRepository.findByCourseNumber(courseNumber)
				.orElseThrow(() -> new CourseNotFoundException(courseNumber));

		Optional<Course> bookedCourse = customer.getCourses().stream()
				.filter(c -> c.getCourseNumber().equals(course.getCourseNumber())).findFirst();

		if (bookedCourse.isPresent()) {
			customer.removeCourse(bookedCourse.get());
			customerRepository.save(customer);
		}

		String customerMail = "customer@domain.com";

		boolean mailWasSent = mailUseCase.sendMail(customerMail, "Oh, we're sorry that you canceled your membership!",
				"Some text to make her/him come back again...");

		if (!mailWasSent) {
			// do some error handling here (including e.g. transaction rollback, etc.)
			// ...

			throw new MembershipMailNotSentException(customerMail);
		}
	}

}
