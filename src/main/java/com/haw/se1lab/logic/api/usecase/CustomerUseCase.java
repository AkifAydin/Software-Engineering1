package com.haw.se1lab.logic.api.usecase;

import java.util.List;

import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.exception.CustomerAlreadyExistingException;
import com.haw.se1lab.common.api.exception.CustomerNotFoundException;
import com.haw.se1lab.dataaccess.api.entity.Customer;

public interface CustomerUseCase {

	List<Customer> findAllCustomers();

	Customer findCustomerByLastName(String lastName) throws CustomerNotFoundException;

	Customer createCustomer(String firstName, String lastName, Gender gender) throws CustomerAlreadyExistingException;

}
