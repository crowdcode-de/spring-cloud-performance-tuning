package io.crowdcode.flaschenhals.customer.repository;

import io.crowdcode.flaschenhals.customer.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("DELETE FROM Employee e WHERE e.userId = :userId")
    int deleteByUserId(@Param("userId") Long userId);

    List<Employee> findByCustomerId(Long customerId);

}
