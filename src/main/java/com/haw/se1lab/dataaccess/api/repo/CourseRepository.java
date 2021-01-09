package com.haw.se1lab.dataaccess.api.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.dataaccess.api.entity.Course;

/**
 * Represents a repository for the management of {@link Course} entities in a database.
 * 
 * @author Arne Busch
 */
// important: no class "<repository name>Impl" implementing this interface and being annotated with @Component required
// -> Spring Data automatically creates a Spring bean for this repository which can then be used using @Autowired
public interface CourseRepository extends JpaRepository<Course, Long> {

	/* ---- Custom Query Methods ---- */

	// For documentation about how query methods work and how to declare them see "Spring Data - Query Methods":
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

	/**
	 * Returns the {@link Course} entity with the given course number.
	 * 
	 * @param name the course number
	 * @return an {@link Optional} containing the found course
	 */
	// custom query method using JPQL query string
	@Query("select c from Course c where c.courseNumber = :courseNumber")
	Optional<Course> findByCourseNumber(@Param("courseNumber") CourseNumber courseNumber);

	/**
	 * Returns the {@link Course} entities with the given name.
	 * 
	 * @param name the name to search for
	 * @return the found courses
	 */
	// custom query method using JPQL query string
	@Query("select c from Course c where c.name = :name")
	List<Course> findByName(@Param("name") String name);

	/**
	 * Deletes the {@link Course} entity with the given course number.
	 * 
	 * @param courseNumber the course number
	 */
	// custom query method with query automatically derived from method name (e.g. "<action>By<attribute name>")
	// equivalent SQL query: delete from COURSE where COURSE_NUMBER = [courseNumber.code]
	@Transactional // causes the method to be executed in a database transaction (required for write operations)
	void deleteByCourseNumber(CourseNumber courseNumber);

}
