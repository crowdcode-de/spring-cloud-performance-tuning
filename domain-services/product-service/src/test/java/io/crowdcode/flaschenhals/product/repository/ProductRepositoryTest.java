package io.crowdcode.flaschenhals.product.repository;

import io.crowdcode.flaschenhals.product.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.crowdcode.flaschenhals.product.fixture.ProductFixture.buildDefaultProduct;
import static io.crowdcode.flaschenhals.product.fixture.ProductFixture.buildProduct;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testPersistProduct() throws Exception {
        Product product = productRepository.save(buildDefaultProduct());
        assertThat(product.getId(), is(notNullValue()));
    }

    @Test
    public void testSaveAndLoadProductWithTags() throws Exception {
        Product product = buildProduct("TEST").addTags("Limonade", "Cola", "Light");
        productRepository.save(product);
        testEntityManager.flush();
        testEntityManager.clear();
        Product found = productRepository.findOne(product.getId());
        assertThat(found.getTags(), hasSize(3));
        assertThat(found.getTags(), containsInAnyOrder("Limonade", "Cola", "Light"));
    }

    @Test
    public void testFindByOneTag() throws Exception {
        testEntityManager.persist(buildProduct("Fritz-Cola").addTags("Limonade", "Cola"));
        testEntityManager.persist(buildProduct("Fritz-Apfel").addTags("Limonade", "Apfelschorle"));
        testEntityManager.persist(buildProduct("Kronbacher Radler").addTags("Bier", "Bier-Mix"));

        List<Product> products = productRepository.findByTag("Cola");
        assertThat(products, hasSize(1));
        assertThat(products.get(0).getTags(), containsInAnyOrder("Limonade", "Cola"));
    }

    @Test
    public void testFindByMultiTags() throws Exception {
        Product p1 = testEntityManager.persist(buildProduct("Fritz-Cola").addTags("Limonade", "Cola"));
        Product p2 = testEntityManager.persist(buildProduct("Fritz-Apfel").addTags("Limonade", "Apfelschorle"));
        Product p3 = testEntityManager.persist(buildProduct("Kronbacher Radler").addTags("Bier", "Bier-Mix"));

        List<Product> products = productRepository.findByTag("Cola", "Bier");
        assertThat(products, hasSize(2));
        assertThat(products, containsInAnyOrder(p1, p3));
    }
}