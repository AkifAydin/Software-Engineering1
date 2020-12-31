package com.haw.se1lab.logic.api.usecase;

import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.exception.CourseNotFoundException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.common.api.exception.MembershipMailNotSentException;
import com.haw.se1lab.dataaccess.api.entity.Course;

/**
 * Defines use case functionality for {@link Course} entities.
 * 
 * @author Arne Busch
 */
public interface CourseUseCase {

	/**
	 * Enrolls a customer in a course.
	 * 
	 * @param customerNumber the customer's customer number
	 * @param courseNumber   the course number of the course to enroll the customer
	 *                       in
	 * @throws CustomerNotFoundException in case the customer could not be found
	 * @throws CourseNotFoundException   in case the course could not be found
	 */
	void enrollInCourse(CustomerNumber customerNumber, CourseNumber courseNumber)
			throws CustomerNotFoundException, CourseNotFoundException;

	/**
	 * Transfers all courses of a customer to another customer. The source
	 * customer's membership to all of his courses is canceled and the target
	 * customer is enrolled in the respective courses.
	 * 
	 * @param fromCustomerNumber the customer number of the source customer to be
	 *                           removed from his courses
	 * @param toCustomerNumber   the customer number of the target customer to get
	 *                           the courses of the source customer
	 * @throws CustomerNotFoundException in case one of the customers could not be
	 *                                   found
	 */
	void transferCourses(CustomerNumber fromCustomerNumber, CustomerNumber toCustomerNumber)
			throws CustomerNotFoundException;

	/**
	 * Cancels a course membership. An e-mail is sent to all possible participants
	 * on the waiting list for this course. If the given customer is not member of
	 * the provided course, nothing changes.
	 * 
	 * @param customerNumber the number of the customer whose membership shall be
	 *                       canceled
	 * @param courseNumber   the number of the course to cancel the membership from
	 * @throws CustomerNotFoundException      in case the customer could not be
	 *                                        found
	 * @throws CourseNotFoundException        in case the course could not be found
	 * @throws MembershipMailNotSentException in case the e-mail with the membership
	 *                                        update could not be sent to the
	 *                                        persons in the waiting list
	 */
	void cancelMembership(CustomerNumber customerNumber, CourseNumber courseNumber)
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException;

}
