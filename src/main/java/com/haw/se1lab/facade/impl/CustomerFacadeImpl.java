package com.haw.se1lab.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.haw.se1lab.common.api.exception.CustomerAlreadyExistingException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.facade.api.CustomerFacade;
import com.haw.se1lab.logic.api.usecase.CustomerUseCase;

/**
 * Default implementation for {@link CustomerFacade}. This implementation uses REST to provide the defined
 * functionality.
 * 
 * @author Arne Busch
 */
@RestController
@RequestMapping(path = "/customers")
public class CustomerFacadeImpl implements CustomerFacade {

	@Autowired
	private CustomerUseCase customerUseCase;

	@GetMapping
	@Override
	public List<Customer> getCustomers() {
		return customerUseCase.findAllCustomers();
	}

	@GetMapping(value = "/{id:[\\d]+}")
	@Override
	public Customer getCustomer(@PathVariable("id") long id) throws CustomerNotFoundException {
		return customerUseCase.findCustomerById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	@Override
	public Customer createCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistingException {
		// check preconditions
		Assert.notNull(customer, "Parameter 'customer' must not be null!");

		return customerUseCase.createCustomer(customer.getCustomerNumber(), customer.getFirstName(),
				customer.getLastName(), customer.getGender());
	}

	@PutMapping
	@Transactional
	@Override
	public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		return customerUseCase.updateCustomer(customer);
	}

	@DeleteMapping("/{id:[\\d]+}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	@Override
	public void deleteCustomer(@PathVariable("id") long id) throws CustomerNotFoundException {
		customerUseCase.deleteCustomer(id);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Customer not found.")
	private void handleCustomerNotFoundException() {
		// nothing to do -> just set the HTTP response status as defined in @ResponseStatus
	}

	@ExceptionHandler(CustomerAlreadyExistingException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Customer already exists.")
	private void handleCustomerAlreadyExistingException() {
		// nothing to do -> just set the HTTP response status as defined in @ResponseStatus
	}

}
