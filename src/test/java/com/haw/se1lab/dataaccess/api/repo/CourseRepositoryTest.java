package com.haw.se1lab.dataaccess.api.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;
import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.dataaccess.api.entity.Course;

/**
 * Test class for {@link CourseRepository}.
 * 
 * @author Arne Busch
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	private Course course;

	@BeforeEach
	public void setUp() {
		// set up fresh test data

		course = new Course(new CourseNumber("SE2"), "Software Engineering 2");
		courseRepository.save(course);
	}

	@AfterEach
	public void tearDown() {
		// clean up test data

		if (course != null && courseRepository.findById(course.getId()).isPresent()) {
			courseRepository.deleteById(course.getId());
		}
	}

	@Test
	public void findByCourseNumber_Success() {
		// [GIVEN]
		CourseNumber courseNumber = course.getCourseNumber();

		// [WHEN]
		Optional<Course> loadedCourse = courseRepository.findByCourseNumber(courseNumber);

		// [THEN]
		assertThat(loadedCourse.isPresent()).isTrue();
		assertThat(loadedCourse.get().getCourseNumber()).isEqualTo(courseNumber);
	}

	@Test
	public void findByCourseNumber_SuccessWithEmptyResult() {
		// [GIVEN]
		CourseNumber courseNumber = new CourseNumber("0000");

		// [WHEN]
		Optional<Course> loadedCourse = courseRepository.findByCourseNumber(courseNumber);

		// [THEN]
		assertThat(loadedCourse.isPresent()).isFalse();
	}

	@Test
	public void findByName_Success() {
		// [GIVEN]
		String courseName = course.getName();

		// [WHEN]
		Optional<Course> loadedCourse = courseRepository.findByName(courseName);

		// [THEN]
		assertThat(loadedCourse.isPresent()).isTrue();
		assertThat(loadedCourse.get().getName()).isEqualTo(courseName);
	}

	@Test
	public void findByName_SuccessWithEmptyResult() {
		// [GIVEN]
		String courseName = "Not-Existing";

		// [WHEN]
		Optional<Course> loadedCourse = courseRepository.findByName(courseName);

		// [THEN]
		assertThat(loadedCourse.isPresent()).isFalse();
	}

}
