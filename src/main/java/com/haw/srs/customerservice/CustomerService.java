package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public void transferReservations(String fromCustomer, String toCustomer) throws CustomerNotFoundException {

// Alternativ zu unten:
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

        to.getReservations().addAll(from.getReservations());
        from.getReservations().clear();

        customerRepository.save(from);
        customerRepository.save(to);
    }
}
