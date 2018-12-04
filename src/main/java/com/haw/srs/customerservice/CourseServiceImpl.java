package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl implements CourseService {

    @Autowired
    private MailGateway mailGateway;

    @Override
    public void cancelMembership(CustomerNumber customerNumber, CourseNumber courseNumber) throws CustomerNotFoundException, CourseNotFoundException {
        // some implementation
        mailGateway.sendMail("customer@domain.com", "Welcome to our course!", "Some welcome text...");
    }
}
