package com.haw.se1lab.facade.api;

import java.util.List;

import com.haw.se1lab.common.api.exception.CustomerAlreadyExistingException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.dataaccess.api.entity.Customer;

public interface CustomerFacade {

	List<Customer> getCustomers();

	Customer getCustomer(Long id) throws CustomerNotFoundException;

	Customer createCustomer(Customer customer) throws CustomerAlreadyExistingException;

	Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

	void deleteCustomer(Long id) throws CustomerNotFoundException;

}
