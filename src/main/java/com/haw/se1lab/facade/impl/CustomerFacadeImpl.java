package com.haw.se1lab.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@RestController
@RequestMapping(path = "/customers")
public class CustomerFacadeImpl implements CustomerFacade {

	@Autowired
	private CustomerUseCase customerUseCase;

	@Override
	@GetMapping
	public List<Customer> getCustomers() {
		return customerUseCase.findAllCustomers();
	}

	@Override
	@GetMapping(value = "/{id:[\\d]+}")
	public Customer getCustomer(@PathVariable("id") Long id) throws CustomerNotFoundException {
		return customerUseCase.findCustomerById(id);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer createCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistingException {
		return customerUseCase.createCustomer(customer.getFirstName(), customer.getLastName(), customer.getGender());
	}

	@Override
	@PutMapping
	public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		return customerUseCase.updateCustomer(customer);
	}

	@Override
	@DeleteMapping("/{id:[\\d]+}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable("id") Long id) throws CustomerNotFoundException {
		customerUseCase.deleteCustomer(id);
	}

}
