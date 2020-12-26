package com.haw.se1lab.dataaccess.api.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;
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

	@BeforeEach
	public void setUp() {
		courseRepository.deleteAll();
		courseRepository.save(new Course("Software Engineering 2"));
	}

	@Test
	public void findByName_Success() {
		// [GIVEN]

		// [WHEN]

		// [THEN]
		Optional<Course> course = courseRepository.findByName("Software Engineering 2");
		assertThat(course.isPresent()).isTrue();
	}

	@Test
	public void findByLastName_SuccessWithEmptyResult() {
		// [GIVEN]

		// [WHEN]

		// [THEN]
		Optional<Course> course = courseRepository.findByName("Not-Existing");
		assertThat(course.isPresent()).isFalse();
	}

}
