package com.haw.se1lab.logic.impl.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.exception.CourseNotFoundException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.common.api.exception.MembershipMailNotSentException;
import com.haw.se1lab.dataaccess.api.entity.Course;
import com.haw.se1lab.dataaccess.api.entity.Customer;
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
	private MailUseCase mailUseCase;

	@Override
	@Transactional
	public void enrollInCourse(String lastName, Course course) throws CustomerNotFoundException {
		Customer customer = customerRepository.findByLastName(lastName)
				.orElseThrow(() -> new CustomerNotFoundException(lastName));
		customer.addCourse(course);
		customerRepository.save(customer);
	}

	@Override
	@Transactional
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
	@Transactional
	public void cancelMembership(CustomerNumber customerNumber, CourseNumber courseNumber)
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// some implementation goes here
		// find customer, find course, look for membership, remove membership, etc.
		// ...

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
