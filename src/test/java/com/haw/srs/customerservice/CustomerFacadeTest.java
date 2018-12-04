package com.haw.srs.customerservice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerFacadeTest {

    private final Log log = LogFactory.getLog(getClass());

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        this.customerRepository.deleteAll();

        customer = this.customerRepository.save(new Customer("Stefan", "Sarstedt", Gender.MALE));

        RestAssured.port = port;
        RestAssured.basePath = "";
    }

    @Test
    void getAllCustomersSuccess() {
        //@formatter:off
        given().
                // add this here to log request --> log().all().
        when().
                get("/customers").
        then().
                // add this here to log response --> log().all().
                statusCode(HttpStatus.OK.value()).
                body("lastName", hasItems("Sarstedt"));
        //@formatter:on
    }

    @Test
    void getCustomerSuccess() {
        //@formatter:off
        given().
        when().
                get("/customers/{id}", customer.getId()).
        then().
                statusCode(HttpStatus.OK.value()).
                body("lastName", equalTo("Sarstedt"));
        //@formatter:on
    }

    @Test
    void getCustomerFailBecauseOfNotFound() {
        //@formatter:off
        given().
        when().
                get("/customers/{id}", Integer.MAX_VALUE).
        then().
                statusCode(HttpStatus.NOT_FOUND.value());
        //@formatter:on
    }

    @Test
    void createCustomerSuccess() {
        //@formatter:off
        given().
                contentType(ContentType.JSON).
                body(new Customer("Stefan", "Sarstedt", Gender.MALE)).
        when().
                post("/customers").
        then().
                statusCode(HttpStatus.CREATED.value()).
                body("id", is(greaterThan(0)));
        //@formatter:on
    }

    @Test
    void updateCustomerSuccess() {
        customer.setFirstName("Stefanie");

        //@formatter:off
        given().
                contentType(ContentType.JSON).
                body(customer).
        when().
                put("/customers").
        then().
                statusCode(HttpStatus.OK.value());

        given().
        when().
                get("/customers/{id}", customer.getId()).
        then().
                statusCode(HttpStatus.OK.value()).
                body("firstName", is(equalTo("Stefanie")));
        //@formatter:on
    }

    @Test
    void deleteCustomerSuccess() {
        //@formatter:off
        given().
                delete("/customers/{id}", customer.getId()).
        then().
                statusCode(HttpStatus.OK.value());

        given().
        when().
                get("/customers/{id}", customer.getId()).
        then().
                statusCode(HttpStatus.NOT_FOUND.value());
        //@formatter:on
    }
}