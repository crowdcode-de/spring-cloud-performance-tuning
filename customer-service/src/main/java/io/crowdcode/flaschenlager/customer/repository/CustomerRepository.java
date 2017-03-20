package io.crowdcode.flaschenlager.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.crowdcode.flaschenlager.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String email);
}
