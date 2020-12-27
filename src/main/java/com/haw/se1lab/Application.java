package com.haw.se1lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.haw.se1lab.common.api.datatype.CourseNumber;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.datatype.PhoneNumber;
import com.haw.se1lab.dataaccess.api.entity.Course;
import com.haw.se1lab.dataaccess.api.entity.CourseReview;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CourseRepository;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;

/**
 * Main application class used for running the application.
 * 
 * @author Arne Busch
 */
@SpringBootApplication
public class Application {

	/**
	 * Starts the application.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

/**
 * Inserts some initial data into the database at startup.
 * 
 * @author Arne Busch
 */
@Component
class InitialDataInsertionRunner implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public void run(String... args) {
		Customer customer = new Customer(new CustomerNumber(1), "Arne", "Busch", Gender.MALE,
				"arne.busch@haw-hamburg.de", new PhoneNumber("+49-40-12345678"));
		customerRepository.save(customer);

		Course course = new Course(new CourseNumber("SE1"), "Software Engineering 1");
		CourseReview review = new CourseReview(customer.getFirstName() + " " + customer.getLastName(), 4,
				"Good introduction!");
		course.addReview(review);
		courseRepository.save(course);

		customer.addCourse(course);
		customer.setLastFinishedCourse(course);
		// no need to save the customer again, as now it's already managed by Hibernate
	}

}
