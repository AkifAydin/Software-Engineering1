package com.haw.srs.customerservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(profiles = "testing")
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository realCustomerRepository;

    @Test
    void getAllCustomersSuccess() {

        realCustomerRepository.deleteAll();

        Customer customer = new Customer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
        realCustomerRepository.save(customer);

        List<Customer> actual = customerService.getCustomers();
        assertThat(actual).size().isEqualTo(1);
        assertThat(actual.get(0).getFirstName()).isEqualTo("Jane");
    }

    @Test
    void transferReservationsSuccess() throws CustomerNotFoundException {

        realCustomerRepository.deleteAll();

        Customer from = new Customer("John", "Smith", Gender.MALE);
        from.addReservation(new Reservation("James Bond 007"));
        from.addReservation(new Reservation("Rosamunde Pilcher"));
        realCustomerRepository.save(from);
        Customer to = new Customer("Eva", "Miller", Gender.FEMALE);
        realCustomerRepository.save(to);

        assertThat(from.getReservations()).size().isEqualTo(2);
        assertThat(to.getReservations()).size().isEqualTo(0);

        customerService.transferReservations(from.getLastName(), to.getLastName());

        // versuche hier from & to von oben zu verwenden...warum klappt es nicht?
        Customer fromRefreshed = realCustomerRepository.findByLastName(from.getLastName()).get();
        Customer toRefreshed = realCustomerRepository.findByLastName(to.getLastName()).get();
        assertThat(fromRefreshed.getReservations()).size().isEqualTo(0);
        assertThat(toRefreshed.getReservations()).size().isEqualTo(2);
    }
}