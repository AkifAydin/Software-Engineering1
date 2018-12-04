//package com.haw.srs.customerservice;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class CourseServiceTest {
//
//    @Autowired
//    private CustomerService customerService;
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @BeforeEach
//    void setup() {
//    }
//
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
//
//    
//
//}