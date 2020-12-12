package com.haw.se1lab.logic.impl.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.exception.CustomerAlreadyExistingException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;
import com.haw.se1lab.logic.api.usecase.CustomerUseCase;

@Service
public class CustomerUseCaseImpl implements CustomerUseCase {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerUseCaseImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomerByLastName(String lastName) throws CustomerNotFoundException {
		return customerRepository.findByLastName(lastName).orElseThrow(() -> new CustomerNotFoundException(lastName));
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, Gender gender)
			throws CustomerAlreadyExistingException {
		if (customerRepository.findByLastName(lastName).isPresent()) {
			throw new CustomerAlreadyExistingException(lastName);
		}

		return customerRepository.save(new Customer(firstName, lastName, gender));
	}

}
