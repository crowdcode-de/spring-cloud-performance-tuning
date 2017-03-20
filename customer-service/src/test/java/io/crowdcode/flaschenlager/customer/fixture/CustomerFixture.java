package io.crowdcode.flaschenlager.customer.fixture;

import java.util.ArrayList;
import java.util.List;

import io.crowdcode.flaschenlager.customer.model.Customer;
import io.crowdcode.flaschenlager.customer.model.Employee;

public class CustomerFixture {

	public static final long FIRST_USERID = 1L;
	public static final long SECOND_USERID = 2L;
	public static final String DEFAULT_EMAIL = "EM@A.IL";

	public static Customer buildDefaultCustomer() {
		Customer customer = new Customer()
				.setName("NAME")
				.setAddress("ADDRESS")
				.setEmail(DEFAULT_EMAIL)
				.setPhone("PHONE");

		List<Employee> employees = new ArrayList<>();
		employees.add(buildEmployee(customer, FIRST_USERID));
		employees.add(buildEmployee(customer, SECOND_USERID));
		customer.setEmployees(employees);

		return customer;
	}

	public static Employee buildEmployee(Customer customer, Long userId) {
		return new Employee().setCustomer(customer).setUserId(userId);
	}



}
