package com.haw.srs.se1lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MailGateway mailGateway;

    @Transactional
    public void enrollInCourse(String lastName, Course course) throws CustomerNotFoundException {
        Customer customer = customerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new CustomerNotFoundException(lastName));

        customer.addCourse(course);
        customerRepository.save(customer);
    }

    @Transactional
    public void transferCourses(String fromCustomerLastName, String toCustomerLastName) throws CustomerNotFoundException {
        Customer from = customerRepository
                .findByLastName(fromCustomerLastName)
                .orElseThrow(() -> new CustomerNotFoundException(fromCustomerLastName));
        Customer to = customerRepository
                .findByLastName(toCustomerLastName)
                .orElseThrow(() -> new CustomerNotFoundException(toCustomerLastName));

        to.getCourses().addAll(from.getCourses());
        from.getCourses().clear();

        customerRepository.save(from);
        customerRepository.save(to);
    }

    /**
     * Cancels a course membership. An Email is sent to all possible participants on the waiting list for this course.
     * If customer is not member of the provided course, the operation is ignored.
     *
     * @throws IllegalArgumentException if customerNumber==null or courseNumber==null
     */
    @Transactional
    public void cancelMembership(CustomerNumber customerNumber, CourseNumber courseNumber) throws CustomerNotFoundException, CourseNotFoundException {

        // some implementation goes here

        mailGateway.sendMail("customer@domain.com", "Welcome to our course!", "Some welcome text...");
    }
}
