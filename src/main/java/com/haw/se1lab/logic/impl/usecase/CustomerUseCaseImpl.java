package com.haw.se1lab.logic.impl.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.exception.CustomerAlreadyExistingException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;
import com.haw.se1lab.logic.api.usecase.CustomerUseCase;

/**
 * Default implementation for {@link CustomerUseCase}.
 * 
 * @author Arne Busch
 */
@Service
public class CustomerUseCaseImpl implements CustomerUseCase {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomerById(long id) throws CustomerNotFoundException {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	public Customer findCustomerByCustomerNumber(CustomerNumber customerNumber) throws CustomerNotFoundException {
		// check preconditions
		Assert.notNull(customerNumber, "Parameter 'customerNumber' must not be null!");

		return customerRepository.findByCustomerNumber(customerNumber)
				.orElseThrow(() -> new CustomerNotFoundException(customerNumber));
	}

	@Override
	public Customer createCustomer(CustomerNumber customerNumber, String firstName, String lastName, Gender gender)
			throws CustomerAlreadyExistingException {
		// check preconditions
		Assert.notNull(customerNumber, "Parameter 'customerNumber' must not be null!");
		Assert.hasText(firstName, "Parameter 'firstName' must contain text!");
		Assert.hasText(lastName, "Parameter 'lastName' must contain text!");
		Assert.notNull(gender, "Parameter 'gender' must not be null!");

		if (customerRepository.findByCustomerNumber(customerNumber).isPresent()) {
			throw new CustomerAlreadyExistingException(customerNumber);
		}

		Customer customer = new Customer(customerNumber, firstName, lastName, gender);
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		// check preconditions
		Assert.notNull(customer, "Parameter 'customer' must not be null!");

		// make sure the customer to be updated exists (throw exception if not)
		findCustomerById(customer.getId());
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(long id) throws CustomerNotFoundException {
		Customer customer = findCustomerById(id);
		customerRepository.delete(customer);
	}

}
