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

import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;

@RestController
@RequestMapping(path = "/customers")
public class CustomerFacadeImpl {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerFacadeImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping(value = "/{id:[\\d]+}")
	public Customer getCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
		return customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
	}

	@DeleteMapping("/{id:[\\d]+}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(customerId));
		customerRepository.delete(customer);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer createCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PutMapping
	public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		// check if customer exists
		customerRepository.findById(customer.getId())
				.orElseThrow(() -> new CustomerNotFoundException(customer.getId()));
		return customerRepository.save(customer);
	}

}
