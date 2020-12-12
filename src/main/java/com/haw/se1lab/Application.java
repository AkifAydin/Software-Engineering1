package com.haw.se1lab;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.datatype.PhoneNumber;
import com.haw.se1lab.dataaccess.api.entity.Course;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Component
class PopulateTestDataRunner implements CommandLineRunner {

	private final CustomerRepository customerRepository;

	@Autowired
	public PopulateTestDataRunner(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) {
		Arrays.asList("Miller,Doe,Smith".split(",")).forEach(
				name -> customerRepository.save(new Customer("Jane", name, Gender.FEMALE, name + "@dummy.org", null)));

		Course course = new Course("Software Engineering 1");

		Customer customer = new Customer("Arne", "Busch", Gender.MALE, "arne.busch@haw-hamburg.de",
				new PhoneNumber("+49-40-12345678"));
		customer.addCourse(course);

		customerRepository.save(customer);
	}

}
