package com.haw.se1lab.dataaccess.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haw.se1lab.dataaccess.api.entity.Course;

/**
 * Represents a repository for the management of {@link Course} entities in a
 * database.
 * 
 * @author Arne Busch
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

	/**
	 * Returns the {@link Course} entity with the given name.
	 * 
	 * @param name the course's name
	 * @return an {@link Optional} containing the found course
	 */
	// @Query("TODO")
	Optional<Course> findByName(String name);

}
