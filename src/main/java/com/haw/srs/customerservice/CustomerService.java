package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerByLastname(String lastName) throws CustomerNotFoundException {
        return customerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new CustomerNotFoundException(lastName));
    }

    public void enrollInCourse(String lastName, Course course) throws CustomerNotFoundException {
        Customer customer = customerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new CustomerNotFoundException(lastName));

        customer.addCourse(course);
        customerRepository.save(customer);
    }

    @Transactional
    public void transferCourses(String fromCustomer, String toCustomer) throws CustomerNotFoundException {
// Alternative Formulierung zu unten:
//        Optional<Customer> from = customerRepository.findByLastName(fromCustomer);
//        if (!from.isPresent()) {
//            throw new CustomerNotFoundException(fromCustomer);
//        }
        Customer from = customerRepository
                .findByLastName(fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(fromCustomer));
        Customer to = customerRepository
                .findByLastName(toCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(toCustomer));

        to.getCourses().addAll(from.getCourses());
        from.getCourses().clear();

        customerRepository.save(from);
        customerRepository.save(to);
    }
}
