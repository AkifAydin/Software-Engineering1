package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Component
@Profile("local")
class PopulateTestDataRunner implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public PopulateTestDataRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {
        Arrays.asList(
                "Miller,Doe,Smith".split(","))
                .forEach(
                        name -> customerRepository.save(new Customer("Jane", name, Gender.FEMALE, name + "@dummy.org", null))
                );

        Customer customer = new Customer("Stefan", "Sarstedt", Gender.MALE, "stefan.sarstedt@haw-hamburg.de", null);
        Reservation reservation = new Reservation("James Bond 007");
        customer.addReservation(reservation);
        customerRepository.save(customer);
    }
}
