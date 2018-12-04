package com.haw.srs.se1lab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByLastName(String lastName);
}
