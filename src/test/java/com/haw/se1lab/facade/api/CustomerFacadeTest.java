package com.haw.se1lab.facade.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;
import com.haw.se1lab.common.api.datatype.CustomerNumber;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.common.api.datatype.PhoneNumber;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Test class for {@link CustomerFacade}.
 * 
 * @author Arne Busch
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerFacadeTest {

	/*
	 * Logging of REST requests/responses for debugging can be enabled like this:
	 * Add this before .when() to log a request or before .then() to log a response:
	 * log().all().
	 * 
	 * Also uncomment the logger in the next line.
	 */
//	private final Log log = LogFactory.getLog(getClass());

	@LocalServerPort
	private int port;

	@Autowired
	private CustomerRepository customerRepository;

	private Customer customer1;

	private Customer customer2;

	@BeforeEach
	public void setUp() {
		// set up fresh test data

		customer1 = new Customer(new CustomerNumber(2), "Jane", "Doe", Gender.FEMALE, "jane.doe@haw-hamburg.de",
				new PhoneNumber("+49", "040", "88888888"));
		customerRepository.save(customer1);

		RestAssured.port = port;
		RestAssured.basePath = "";
	}

	@AfterEach
	public void tearDown() {
		// clean up test data

		if (customer1 != null && customerRepository.findById(customer1.getId()).isPresent()) {
			customerRepository.deleteById(customer1.getId());
		}

		if (customer2 != null && customerRepository.findByCustomerNumber(customer2.getCustomerNumber()).isPresent()) {
			customerRepository.deleteByCustomerNumber(customer2.getCustomerNumber());
		}
	}

	@Test
	public void getCustomers_Success() {
		// @formatter:off
		// [GIVEN]
		given()

		// [WHEN]
		.when()
		.get("/customers")

		// [THEN]
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("lastName", hasItems(customer1.getLastName()));
		// @formatter:on
	}

	@Test
	public void getCustomer_Success() {
		// @formatter:off
		// [GIVEN]
		given()

		// [WHEN]
		.when()
		.get("/customers/{id}", customer1.getId())

		// [THEN]
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("lastName", equalTo(customer1.getLastName()));
		// @formatter:on
	}

	@Test
	public void getCustomer_FailBecauseNotFound() {
		// @formatter:off
		// [GIVEN]
		given()

		// [WHEN]
		.when()
		.get("/customers/{id}", Integer.MAX_VALUE)

		// [THEN]
		.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
		// @formatter:on
	}

	@Test
	public void createCustomer_Success() {
		// @formatter:off
		// [GIVEN]
		customer2 = new Customer(new CustomerNumber(3), "John", "Smith", Gender.MALE);
		
		given()
		.contentType(ContentType.JSON)
		.body(customer2)

		// [WHEN]
		.when()
		.post("/customers")

		// [THEN]
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.body("id", is(greaterThan(0)));
		// @formatter:on
	}

	@Test
	public void createCustomer_FailBecauseAlreadyExisting() {
		// @formatter:off
		// [GIVEN]
		Customer customer1Duplicate = new Customer(new CustomerNumber(2), "Jane", "Doe", Gender.FEMALE);
		
		given()
		.contentType(ContentType.JSON)
		.body(customer1Duplicate)

		// [WHEN]
		.when()
		.post("/customers")

		// [THEN]
		.then()
		.statusCode(HttpStatus.BAD_REQUEST.value());
		// @formatter:on
	}

	@Test
	public void updateCustomer_Success() {
		// @formatter:off
		// [GIVEN]
		String newFirstName = "Jennifer";
		customer1.setFirstName(newFirstName);
		
		given()
		.contentType(ContentType.JSON)
		.body(customer1)

		// [WHEN]
		.when()
		.put("/customers")

		// [THEN]
		.then()
		.statusCode(HttpStatus.OK.value());

		// [GIVEN]
		given()

		// [WHEN]
		.when()
		.get("/customers/{id}", customer1.getId())

		// [THEN]
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("firstName", is(equalTo(newFirstName)));
		// @formatter:on
	}

	@Test
	public void deleteCustomer_Success() {
		// @formatter:off
		// [GIVEN]
		given()

		// [WHEN]
		.delete("/customers/{id}", customer1.getId())

		// [THEN]
		.then()
		.statusCode(HttpStatus.OK.value());

		// [GIVEN]
		given()

		// [WHEN]
		.when()
		.get("/customers/{id}", customer1.getId())

		// [THEN]
		.then()
		.statusCode(HttpStatus.NOT_FOUND.value());
		// @formatter:on
	}

}
