package com.haw.se1lab.dataaccess.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haw.se1lab.dataaccess.api.entity.Course;

/**
 * Represents a repository for the management of {@link Course} entities in a
 * database.
 * 
 * @author Arne Busch
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

}
