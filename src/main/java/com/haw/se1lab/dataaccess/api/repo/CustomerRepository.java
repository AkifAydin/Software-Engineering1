package com.haw.se1lab.dataaccess.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haw.se1lab.dataaccess.api.entity.Customer;

/**
 * Represents a repository for the management of {@link Customer} entities in a
 * database.
 * 
 * @author Arne Busch
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	/**
	 * Returns the {@link Customer} entity with the given last name.
	 * 
	 * @param lastName the customer's last name
	 * @return an {@link Optional} containing the found customer
	 */
	Optional<Customer> findByLastName(String lastName);

}
