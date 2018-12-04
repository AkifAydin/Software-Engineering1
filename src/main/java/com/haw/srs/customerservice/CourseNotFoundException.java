package com.haw.srs.customerservice;

import lombok.Value;

@Value
class CourseNotFoundException extends Exception {

    private final int courseNumber;

    CourseNotFoundException(int courseNumber) {
        super(String.format("Could not find course with number %d.", courseNumber));

        this.courseNumber = courseNumber;
    }
}