package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerFacade {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerFacade(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping(value = "/{id:[\\d]+}")
    public Customer getCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        return customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @DeleteMapping("/{id:[\\d]+}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository
                .findById(customerId)
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
        Customer customerToUpdate = customerRepository
                .findById(customer.getId())
                .orElseThrow(() -> new CustomerNotFoundException(customer.getId()));

        return customerRepository.save(customer);
    }
}
