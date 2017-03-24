package io.crowdcode.flaschenlager.product.repository;

import io.crowdcode.flaschenlager.product.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testPersistProduct() throws Exception {
        Product product = new Product()
                .setName("Name of the Product")
                .setDescription("Description of the Product")
                .setUnit("liter")
                .setAmount(3.0d);
        productRepository.save(product);

        assertThat(product.getId(), is(notNullValue()));
    }
}