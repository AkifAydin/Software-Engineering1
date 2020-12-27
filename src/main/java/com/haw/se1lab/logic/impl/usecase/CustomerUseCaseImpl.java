package com.haw.se1lab.logic.impl.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Customer findCustomerById(Long id) throws CustomerNotFoundException {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	public Customer findCustomerByLastName(String lastName) throws CustomerNotFoundException {
		return customerRepository.findByLastName(lastName).orElseThrow(() -> new CustomerNotFoundException(lastName));
	}

	@Override
	public Customer createCustomer(CustomerNumber customerNumber, String firstName, String lastName, Gender gender)
			throws CustomerAlreadyExistingException {
		if (customerRepository.findByLastName(lastName).isPresent()) {
			throw new CustomerAlreadyExistingException(lastName);
		}

		Customer customer = new Customer(customerNumber, firstName, lastName, gender);
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		// make sure the customer to be updated exists
		findCustomerById(customer.getId());
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long id) throws CustomerNotFoundException {
		Customer customer = findCustomerById(id);
		customerRepository.delete(customer);
	}

}
