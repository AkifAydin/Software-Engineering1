package com.haw.se1lab.facade.api;

import java.util.List;

import com.haw.se1lab.common.api.exception.CustomerAlreadyExistingException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.dataaccess.api.entity.Customer;

/**
 * Represents a facade for the system operations for customers which are
 * available from outside the system.
 * 
 * @author Arne Busch
 */
public interface CustomerFacade {

	/**
	 * Returns all available customers.
	 * 
	 * @return the found customers
	 */
	List<Customer> getCustomers();

	/**
	 * Returns the customer with the given ID.
	 * 
	 * @param id the customer's technical ID
	 * @return the found customer
	 * @throws CustomerNotFoundException in case the customer could not be found
	 */
	Customer getCustomer(Long id) throws CustomerNotFoundException;

	/**
	 * Creates a customer with the given data.
	 * 
	 * @param customer the customer to be created
	 * @return the created customer
	 * @throws CustomerAlreadyExistingException in case a customer with the given
	 *                                          data already exists
	 */
	Customer createCustomer(Customer customer) throws CustomerAlreadyExistingException;

	/**
	 * Updates a customer with the given data.
	 * 
	 * @param customer the customer to be updated
	 * @return the updated customer
	 * @throws CustomerNotFoundException in case the customer could not be found
	 */
	Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

	/**
	 * Deletes the customer with the given ID.
	 * 
	 * @param id the customer's technical ID
	 * @throws CustomerNotFoundException in case the customer could not be found
	 */
	void deleteCustomer(Long id) throws CustomerNotFoundException;

}
