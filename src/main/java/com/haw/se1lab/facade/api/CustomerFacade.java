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
	 * @return the found customers or an empty list if none were found
	 */
	List<Customer> getCustomers();

	/**
	 * Returns the customer with the given ID.
	 * 
	 * @param id the customer's technical ID
	 * @return the found customer
	 * @throws CustomerNotFoundException in case the customer could not be found
	 */
	Customer getCustomer(long id) throws CustomerNotFoundException;

	/**
	 * Creates a customer with the given data.
	 * 
	 * @param customer the customer to be created; must not be <code>null</code>
	 * @return the created customer
	 * @throws CustomerAlreadyExistingException in case a customer with the given
	 *                                          data already exists
	 */
	Customer createCustomer(Customer customer) throws CustomerAlreadyExistingException;

	/**
	 * Updates a customer with the given data.
	 * 
	 * @param customer the customer to be updated; must not be <code>null</code>
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
	void deleteCustomer(long id) throws CustomerNotFoundException;

}
