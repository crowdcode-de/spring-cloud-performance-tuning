package io.crowdcode.flaschenhals.customer.repository;

import io.crowdcode.flaschenhals.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
}
