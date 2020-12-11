package com.haw.srs.se1lab;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByLastName(String lastName);

}
