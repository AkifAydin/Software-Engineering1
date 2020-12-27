package com.haw.se1lab.facade.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

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

//	private final Log log = LogFactory.getLog(getClass());

	@LocalServerPort
	private int port;

	@Autowired
	private CustomerRepository customerRepository;

	private Customer customer;

	@BeforeEach
	public void setUp() {
		customerRepository.deleteAll();
		customer = customerRepository.save(new Customer(new CustomerNumber(1), "Arne", "Busch", Gender.MALE));

		RestAssured.port = port;
		RestAssured.basePath = "";
	}

	@Test
	public void getCustomers_Success() {
		// @formatter:off
		// [GIVEN]
		given()
				// add this here to log request --> log().all().

				// [WHEN]
				.when().get("/customers")

				// [THEN]
				// add this here to log response --> log().all().
				.then().statusCode(HttpStatus.OK.value()).body("lastName", hasItems("Busch"));
		// @formatter:on
	}

	@Test
	public void getCustomer_Success() {
		// @formatter:off
		// [GIVEN]
		given()

				// [WHEN]
				.when().get("/customers/{id}", customer.getId())

				// [THEN]
				.then().statusCode(HttpStatus.OK.value()).body("lastName", equalTo("Busch"));
		// @formatter:on
	}

	@Test
	public void getCustomer_FailBecauseNotFound() {
		// @formatter:off
		// [GIVEN]
		given()

				// [WHEN]
				.when().get("/customers/{id}", Integer.MAX_VALUE)

				// [THEN]
				.then().statusCode(HttpStatus.NOT_FOUND.value());
		// @formatter:on
	}

	@Test
	public void createCustomer_Success() {
		// @formatter:off
		// [GIVEN]
		given().contentType(ContentType.JSON).body(new Customer(new CustomerNumber(2), "Jane", "Doe", Gender.FEMALE))

				// [WHEN]
				.when().post("/customers")

				// [THEN]
				.then().statusCode(HttpStatus.CREATED.value()).body("id", is(greaterThan(0)));
		// @formatter:on
	}

	@Test
	public void createCustomer_FailBecauseAlreadyExisting() {
		// @formatter:off
		// [GIVEN]
		given().contentType(ContentType.JSON).body(new Customer(new CustomerNumber(1), "Arne", "Busch", Gender.MALE))

				// [WHEN]
				.when().post("/customers")

				// [THEN]
				.then().statusCode(HttpStatus.BAD_REQUEST.value());
		// @formatter:on
	}

	@Test
	public void updateCustomer_Success() {
		// @formatter:off
		// [GIVEN]
		customer.setFirstName("Anne");
		given().contentType(ContentType.JSON).body(customer)

				// [WHEN]
				.when().put("/customers")

				// [THEN]
				.then().statusCode(HttpStatus.OK.value());

		// [GIVEN]
		given()

				// [WHEN]
				.when().get("/customers/{id}", customer.getId())

				// [THEN]
				.then().statusCode(HttpStatus.OK.value()).body("firstName", is(equalTo("Anne")));
		// @formatter:on
	}

	@Test
	public void deleteCustomer_Success() {
		// @formatter:off
		// [GIVEN]
		given()

				// [WHEN]
				.delete("/customers/{id}", customer.getId())

				// [THEN]
				.then().statusCode(HttpStatus.OK.value());

		// [GIVEN]
		given()

				// [WHEN]
				.when().get("/customers/{id}", customer.getId())

				// [THEN]
				.then().statusCode(HttpStatus.NOT_FOUND.value());
		// @formatter:on
	}

}
