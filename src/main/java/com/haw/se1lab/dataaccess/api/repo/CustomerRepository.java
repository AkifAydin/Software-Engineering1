package com.haw.se1lab.dataaccess.api.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.dataaccess.api.entity.Course;
import com.haw.se1lab.dataaccess.api.entity.Customer;

/**
 * Represents a repository for the management of {@link Customer} entities in a database.
 * 
 * @author Arne Busch
 */
// important: no class "<repository name>Impl" implementing this interface and being annotated with @Component required
// -> Spring Data automatically creates a Spring bean for this repository which can then be used using @Autowired
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/* ---- Custom Query Methods ---- */

	// For documentation about how query methods work and how to declare them see "Spring Data - Query Methods":
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

	/**
	 * Returns the {@link Customer} entity with the given customer number.
	 * 
	 * @param customerNumber the customer number
	 * @return an {@link Optional} containing the found customer
	 */
	// custom query method with query automatically derived from method name (e.g. "<action>By<attribute name>")
	// equivalent SQL query: select * from CUSTOMER where NUMBER = [customerNumber.number]
	Optional<Customer> findByCustomerNumber(CustomerNumber customerNumber);

	/**
	 * Returns the {@link Customer} entity with the given customer number.
	 * 
	 * @param customerNumber the customer number
	 * @return an {@link Optional} containing the found customer
	 */
	// custom query method with query automatically derived from method name (e.g. "<action>By<attribute name>")
	// equivalent SQL query:
	// select * from CUSTOMER c
	// where FIRST_NAME = [firstName]
	// and LAST_NAME = [lastName]
	// and [course.id] in (select COURSES_ID from CUSTOMER_COURSES where CUSTOMER_ID = c.ID)
	// and EMAIL is not null
	// order by GENDER desc
	List<Customer> findByFirstNameAndLastNameAndCoursesContainingAndEmailNotNullOrderByGenderDesc(String firstName,
			String lastName, Course course);

	/**
	 * Deletes the {@link Customer} entity with the given customer number.
	 * 
	 * @param customerNumber the customer number
	 */
	// custom query method with query automatically derived from method name (e.g. "<action>By<attribute name>")
	// equivalent SQL query: delete from CUSTOMER where NUMBER = [customerNumber.number]
	@Transactional // causes the method to be executed in a database transaction (required for write operations)
	void deleteByCustomerNumber(CustomerNumber customerNumber);

}
