package io.crowdcode.flaschenlager.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.crowdcode.flaschenlager.customer.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Modifying
	@Query("DELETE FROM Employee e WHERE e.userId = :userId")
	int deleteByUserId(@Param("userId") Long userId);

	List<Employee> findByCustomerId(Long customerId);

}
