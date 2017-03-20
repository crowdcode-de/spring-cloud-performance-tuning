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
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void findByEmail() throws Exception {
		Customer customer = entityManager.persist(CustomerFixture.buildDefaultCustomer());

		assertThat(customerRepository.findByEmail(CustomerFixture.DEFAULT_EMAIL), is(customer));
	}

}