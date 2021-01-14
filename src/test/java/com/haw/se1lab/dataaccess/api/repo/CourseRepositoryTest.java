package com.haw.se1lab.dataaccess.api.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;
import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.dataaccess.api.entity.Course;

/**
 * Test class for {@link CourseRepository}.
 * 
 * @author Arne Busch
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	private Course course1;

	private Course course2;

	@BeforeAll
	public static void setUpAll() {
		// actions to be performed once before execution of first test method

	}

	@AfterAll
	public static void tearDownAll() {
		// actions to be performed once after execution of last test method

	}

	@BeforeEach
	public void setUp() {
		// set up fresh test data before each test method execution

		// SE2 for study course "Angewandte Informatik"
		course1 = new Course(new CourseNumber("SE2"), "Software Engineering 2");
		courseRepository.save(course1);

		// SE2 for study course "Wirtschaftsinformatik"
		course2 = new Course(new CourseNumber("SE2WI"), "Software Engineering 2");
		courseRepository.save(course2);
	}

	@AfterEach
	public void tearDown() {
		// clean up test data after each test method execution

		courseRepository.deleteAll();
	}

	@Test
	public void findByCourseNumber_Success() {
		// [GIVEN]
		CourseNumber courseNumber = course1.getCourseNumber();

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
		String courseName = course1.getName();
		CourseNumber courseNumber1 = course1.getCourseNumber();
		CourseNumber courseNumber2 = course2.getCourseNumber();

		// [WHEN]
		List<Course> loadedCourses = courseRepository.findByName(courseName);

		// [THEN]
		assertThat(loadedCourses).hasSize(2);
		assertThat(loadedCourses).extracting(Course::getCourseNumber).containsOnlyOnce(courseNumber1);
		assertThat(loadedCourses).extracting(Course::getCourseNumber).containsOnlyOnce(courseNumber2);
	}

	@Test
	public void findByName_SuccessWithEmptyResult() {
		// [GIVEN]
		String courseName = "Not-Existing";

		// [WHEN]
		List<Course> loadedCourses = courseRepository.findByName(courseName);

		// [THEN]
		assertThat(loadedCourses.isEmpty()).isTrue();
	}

	@Test
	public void deleteByCourseNumber_Success() {
		// [GIVEN]
		CourseNumber courseNumber = course1.getCourseNumber();

		// [WHEN]
		courseRepository.deleteByCourseNumber(courseNumber);

		// [THEN]
		Optional<Course> loadedCourse = courseRepository.findByCourseNumber(courseNumber);
		assertThat(loadedCourse.isPresent()).isFalse();
	}

	@Test
	public void deleteByCourseNumber_SuccessWithNoActualDeletion() {
		// [GIVEN]
		CourseNumber courseNumber = new CourseNumber("0000");

		// [WHEN]
		courseRepository.deleteByCourseNumber(courseNumber);

		// [THEN]
		List<Course> loadedCourses = courseRepository.findAll();
		assertThat(loadedCourses).hasSize(2);
	}

}
