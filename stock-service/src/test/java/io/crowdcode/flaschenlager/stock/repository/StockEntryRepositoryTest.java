package io.crowdcode.flaschenlager.stock.repository;


import io.crowdcode.flaschenlager.stock.fixture.StockFixture;
import io.crowdcode.flaschenlager.stock.model.Stock;
import io.crowdcode.flaschenlager.stock.model.StockEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StockEntryRepositoryTest {


    @Autowired
    private StockEntryRepository stockEntryRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void testCreateStockEntry() throws Exception {
        Stock stock = testEntityManager.persist(new Stock().setName("STOCK_NAME"));
        StockEntry entry = StockFixture.buildStockEntry(1l, 2, 1.2).setStock(stock);

        stockEntryRepository.save(entry);

        assertThat(entry.getId(), is(notNullValue()));
    }

    @Test
    public void testFindStock() throws Exception {
        Stock stock = testEntityManager.persist(new Stock().setName("STOCK_NAME"));
        Long entryId = testEntityManager.persistAndGetId(StockFixture.buildStockEntry(1l, 2, 1.2).setStock(stock), Long.class);
        StockEntry entry = stockEntryRepository.findOne(entryId);

        assertThat(entry.getQuantity(), is(2l));
        assertThat(entry.getPrice(), is(BigDecimal.valueOf(1.2)));
        assertThat(entry.getProductId(), is(1l));

    }
}