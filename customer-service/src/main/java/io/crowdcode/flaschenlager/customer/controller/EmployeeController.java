package io.crowdcode.flaschenlager.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.crowdcode.flaschenlager.customer.model.Customer;
import io.crowdcode.flaschenlager.customer.model.Employee;
import io.crowdcode.flaschenlager.customer.repository.CustomerRepository;
import io.crowdcode.flaschenlager.customer.repository.EmployeeRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@ApiOperation("Remove user as employee from customer.")
	@DeleteMapping("/{customerId}/employee/{userId}")
	public void deleteEmployee(
			@ApiParam("CustomerId") @PathVariable("customerId") long customerId,
			@ApiParam("UserId") @PathVariable("userId") long userId) {
		employeeRepository.deleteByUserId(userId);
	}

	@ApiOperation("Add user as employee from customer.")
	@PutMapping("/{customerId}/employee/{userId}")
	public void addAsEmployee(
			@ApiParam("CustomerId") @PathVariable("customerId") long customerId,
			@ApiParam("UserId") @PathVariable("userId") long userId) {

		Customer customer = customerRepository.getOne(customerId);
		Employee employee = new Employee()
				.setCustomer(customer)
				.setUserId(userId);

		customer.getEmployees().add(employee);

		customerRepository.save(customer);
	}

}
