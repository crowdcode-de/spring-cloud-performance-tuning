package io.crowdcode.flaschenlager.stock.repository;

import io.crowdcode.flaschenlager.stock.model.Stock;
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
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateStock() throws Exception {
        Stock stock = new Stock().setName("H7 Münster");

        stockRepository.save(stock);

        assertThat(stock.getId(), is(notNullValue()));
        assertThat(stock.getVersion(), is(notNullValue()));
    }

    @Test
    public void testFindOne() throws Exception {
        Long stockId = testEntityManager.persistAndGetId(new Stock().setName("STOCK_NAME"), Long.class);

        Stock stock = stockRepository.findOne(stockId);
        assertThat(stock.getName(), is("STOCK_NAME"));
    }
}