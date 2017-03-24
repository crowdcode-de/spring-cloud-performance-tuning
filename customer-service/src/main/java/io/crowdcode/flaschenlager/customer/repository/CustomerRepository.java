package io.crowdcode.flaschenlager.customer.repository;

import io.crowdcode.flaschenlager.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
}
