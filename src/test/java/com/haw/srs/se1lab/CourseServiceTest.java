package com.haw.srs.se1lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CourseServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
    }

    @Test
    void enrollCustomerInCourseSuccess() throws CustomerNotFoundException {
        Customer customer = new Customer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
        customerRepository.save(customer);

        assertThat(customer.getCourses()).size().isEqualTo(0);

        courseService.enrollInCourse(customer.getLastName(), new Course("Software Engineering 1"));

        assertThat(customerService.findCustomerByLastname(customer.getLastName()).getCourses())
                .size().isEqualTo(1);
    }

    @Test
    void enrollCustomerInCourseFailBecauseOfCustomerNotFound() {
        assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> courseService.enrollInCourse("notExisting", new Course("Software Engineering 1")))
                .withMessageContaining("Could not find customer with lastname notExisting.");
    }

    @Test
    void transferCoursesSuccess() throws CustomerNotFoundException {
        Customer from = new Customer("John", "Smith", Gender.MALE);
        from.addCourse(new Course("Software Engineering 1"));
        from.addCourse(new Course("Software Engineering 2"));
        customerRepository.save(from);
        Customer to = new Customer("Eva", "Miller", Gender.FEMALE);
        customerRepository.save(to);

        assertThat(from.getCourses()).size().isEqualTo(2);
        assertThat(to.getCourses()).size().isEqualTo(0);

        courseService.transferCourses(from.getLastName(), to.getLastName());

        assertThat(customerService.findCustomerByLastname(from.getLastName()).getCourses())
                .size().isEqualTo(0);
        assertThat(customerService.findCustomerByLastname(to.getLastName()).getCourses())
                .size().isEqualTo(2);
    }

//    @Test
//    void testCancelOk() {
//        Customer customer = customerRepository.save(new Customer("Jane", "Doe", Gender.FEMALE));
//        Course course = new Course("SE1");
//        try {
//            courseService.cancelMembership(new CustomerNumber(customer.getId()), new CourseNumber(1L));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testCancelNok() {
//        Course course = new Course("SE1");
//        try {
//            courseService.cancelMembership(new CustomerNumber(10L), new CourseNumber(course.getId()));
//        } catch (CustomerNotFoundException e) {
//            assertThat(e.getCustomerId()).isEqualTo(10L);
//        } catch (CourseNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


}