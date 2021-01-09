package com.haw.se1lab.logic.api.usecase;

import java.util.List;

import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.exception.CustomerAlreadyExistingException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.dataaccess.api.entity.Customer;

/**
 * Defines use case functionality for {@link Customer} entities.
 * 
 * @author Arne Busch
 */
public interface CustomerUseCase {

	/**
	 * Returns all available customers.
	 * 
	 * @return the found customers or an empty list if none were found
	 */
	List<Customer> findAllCustomers();

	/**
	 * Returns the customer with the given ID.
	 * 
	 * @param id the customer's technical ID
	 * @return the found customer
	 * @throws CustomerNotFoundException in case the customer could not be found
	 */
	Customer findCustomerById(long id) throws CustomerNotFoundException;

	/**
	 * Returns the customer with the given customer number.
	 * 
	 * @param customerNumber the customer's customer number; must not be <code>null</code>
	 * @return the found customer
	 * @throws CustomerNotFoundException in case the customer could not be found
	 */
	Customer findCustomerByCustomerNumber(CustomerNumber customerNumber) throws CustomerNotFoundException;

	/**
	 * Creates a customer with the given data.
	 * 
	 * @param customerNumber the customer's customer number; must not be <code>null</code>
	 * @param firstName      the customer's first name; must contain text
	 * @param lastName       the customer's last name; must contain text
	 * @param gender         the customer's gender; must not be <code>null</code>
	 * @return the created customer
	 * @throws CustomerAlreadyExistingException in case a customer with the given customer number already exists
	 */
	Customer createCustomer(CustomerNumber customerNumber, String firstName, String lastName, Gender gender)
			throws CustomerAlreadyExistingException;

	/**
	 * Updates a customer with the given data.
	 * 
	 * @param customer the customer data to be updated; must not be <code>null</code>
	 * @return the updated customer
	 * @throws CustomerNotFoundException in case the customer could not be found
	 */
	Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

	/**
	 * Deletes the customer with the given ID.
	 * 
	 * @param id the customer's technical ID; must be the ID of an existing customer
	 * @throws CustomerNotFoundException in case the customer could not be found
	 */
	void deleteCustomer(long id) throws CustomerNotFoundException;

}
