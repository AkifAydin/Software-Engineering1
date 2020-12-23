package com.haw.se1lab.logic.api.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haw.se1lab.Application;
import com.haw.se1lab.common.api.datatype.Gender;
import com.haw.se1lab.dataaccess.api.entity.Customer;
import com.haw.se1lab.dataaccess.api.repo.CustomerRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CustomerUseCaseTest {

	@Autowired
	private CustomerUseCase customerUseCase;

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	public void setUp() {
		customerRepository.deleteAll();
	}

	@Test
	public void findAllCustomers_Success() {
		// [GIVEN]
		Customer customer = new Customer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
		customerRepository.save(customer);

		// [WHEN]
		List<Customer> customers = customerUseCase.findAllCustomers();

		// [THEN]
		assertThat(customers).size().isEqualTo(1);
		assertThat(customers.get(0).getFirstName()).isEqualTo("Jane");
	}

}
