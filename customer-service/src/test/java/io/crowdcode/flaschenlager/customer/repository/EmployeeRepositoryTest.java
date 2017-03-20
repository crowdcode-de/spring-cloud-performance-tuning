package io.crowdcode.flaschenlager.customer.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import io.crowdcode.flaschenlager.customer.fixture.CustomerFixture;
import io.crowdcode.flaschenlager.customer.model.Customer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testDeleteByUserId() throws Exception {
		Customer customer = entityManager
				.persist(CustomerFixture.buildDefaultCustomer());

		assertThat(employeeRepository.findByCustomerId(customer.getId()),
				hasSize(2));

		employeeRepository.deleteByUserId(CustomerFixture.FIRST_USERID);

		assertThat(employeeRepository.findByCustomerId(customer.getId()),
				hasSize(1));

	}

}