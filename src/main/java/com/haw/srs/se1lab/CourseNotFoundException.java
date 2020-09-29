package com.haw.srs.se1lab;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
class CourseNotFoundException extends Exception {

    private final int courseNumber;

    CourseNotFoundException(int courseNumber) {
        super(String.format("Could not find course with number %d.", courseNumber));

        this.courseNumber = courseNumber;
    }
}