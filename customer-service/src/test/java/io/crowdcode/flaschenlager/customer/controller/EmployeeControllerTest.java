package io.crowdcode.flaschenlager.customer.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.crowdcode.flaschenlager.customer.fixture.CustomerFixture;
import io.crowdcode.flaschenlager.customer.model.Customer;
import io.crowdcode.flaschenlager.customer.repository.CustomerRepository;
import io.crowdcode.flaschenlager.customer.repository.EmployeeRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Test
	public void testDeleteEmployeeReference() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/customers/{customerId}/employee/{userId}", 1l,2l)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(employeeRepository, times(1) ).deleteByUserId(2l);
	}

	@Test
	public void testAddAsEmployeeReference() throws Exception {
		when(customerRepository.getOne(any())).thenReturn(CustomerFixture.buildDefaultCustomer());
		mvc.perform(MockMvcRequestBuilders.put("/customers/{customerId}/employee/{userId}", 1l,5l)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(customerRepository, times(1) ).getOne(1l);
		verify(customerRepository, times(1) ).save(any(Customer.class));



	}
}