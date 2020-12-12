package com.haw.se1lab.logic.api.usecase;

import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.exception.CourseNotFoundException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.common.api.exception.MembershipMailNotSentException;
import com.haw.se1lab.dataaccess.api.entity.Course;

public interface CourseUseCase {

	void enrollInCourse(String lastName, Course course) throws CustomerNotFoundException;

	void transferCourses(String fromCustomerLastName, String toCustomerLastName) throws CustomerNotFoundException;

	/**
	 * Cancels a course membership. An e-mail is sent to all possible participants
	 * on the waiting list for this course. If customer is not member of the
	 * provided course, the operation is ignored.
	 *
	 * @throws IllegalArgumentException if customerNumber or courseNumber is <null>
	 */
	void cancelMembership(CustomerNumber customerNumber, CourseNumber courseNumber)
			throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSentException;

}
