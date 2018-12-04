package com.haw.srs.customerservice;

public interface CourseService {
    /**
     * Cancels a course membership. An Email is sent to all possible participants on the waiting list for this course.
     * If customer is not member of the provided course, the operation is ignored.
     *
     * @throws IllegalArgumentException if customerNumber==null or courseNumber==null
     */
    void cancelMembership(CustomerNumber customerNumber, CourseNumber courseNumber) throws CustomerNotFoundException, CourseNotFoundException;
}
