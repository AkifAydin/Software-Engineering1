package com.haw.srs.se1lab;

import lombok.Value;

@Value
class CourseNotFoundException extends Exception {

    private final int courseNumber;

    CourseNotFoundException(int courseNumber) {
        super(String.format("Could not find course with number %d.", courseNumber));

        this.courseNumber = courseNumber;
    }
}