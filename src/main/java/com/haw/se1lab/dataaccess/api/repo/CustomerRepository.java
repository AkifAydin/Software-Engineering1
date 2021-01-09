package com.haw.se1lab.dataaccess.api.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haw.se1lab.common.api.datatype.CustomerNumber;
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
	// See also "Spring Data - Query Methods":
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

	/**
	 * Returns the {@link Customer} entity with the given customer number.
	 * 
	 * @param customerNumber the customer number
	 * @return an {@link Optional} containing the found customer
	 */
	// custom query method with query automatically derived from method name
	// equivalent SQL query: select * from CUSTOMER where NUMBER = [customerNumber.number]
	Optional<Customer> findByCustomerNumber(CustomerNumber customerNumber);

	/**
	 * Deletes the {@link Customer} entity with the given customer number.
	 * 
	 * @param customerNumber the customer number
	 */
	// custom query method with query automatically derived from method name
	// equivalent SQL query: delete from CUSTOMER where NUMBER = [customerNumber.number]
	@Transactional // causes the method to be executed in a database transaction (required for write operations)
	void deleteByCustomerNumber(CustomerNumber customerNumber);

}
