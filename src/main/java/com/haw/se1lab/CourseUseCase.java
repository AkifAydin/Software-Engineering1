package com.haw.se1lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseUseCase {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MailUseCase mailUseCase;

	@Transactional
	public void enrollInCourse(String lastName, Course course) throws CustomerNotFoundException {
		Customer customer = customerRepository.findByLastName(lastName)
				.orElseThrow(() -> new CustomerNotFoundException(lastName));
		customer.addCourse(course);
		customerRepository.save(customer);
	}

	@Transactional
	public void transferCourses(String fromCustomerLastName, String toCustomerLastName)
			throws CustomerNotFoundException {
		Customer from = customerRepository.findByLastName(fromCustomerLastName)
				.orElseThrow(() -> new CustomerNotFoundException(fromCustomerLastName));
		Customer to = customerRepository.findByLastName(toCustomerLastName)
				.orElseThrow(() -> new CustomerNotFoundException(toCustomerLastName));

		to.getCourses().addAll(from.getCourses());
		from.getCourses().clear();

		customerRepository.save(from);
		customerRepository.save(to);
	}

	/**
	 * Cancels a course membership. An e-mail is sent to all possible participants
	 * on the waiting list for this course. If customer is not member of the
	 * provided course, the operation is ignored.
	 *
	 * @throws IllegalArgumentException if customerNumber or courseNumber is <null>
	 */
	@Transactional
	public void cancelMembership(CustomerNumber customerNumber, CourseNumber courseNumber)
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException {
		// some implementation goes here
		// find customer, find course, look for membership, remove membership, etc.
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
