package io.crowdcode.flaschenlager.stock.repository;


import io.crowdcode.flaschenlager.stock.model.Stock;
import io.crowdcode.flaschenlager.stock.model.StockEntry;
import io.crowdcode.flaschenlager.stock.model.StockEntryQuantity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static io.crowdcode.flaschenlager.stock.fixture.StockFixture.buildStock;
import static io.crowdcode.flaschenlager.stock.fixture.StockFixture.buildStockEntry;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        StockEntry entry = buildStockEntry(1l, 2, 1.2).setStock(stock);

        stockEntryRepository.save(entry);

        assertThat(entry.getId(), is(notNullValue()));
    }

    @Test
    public void testFindStock() throws Exception {
        Stock stock = testEntityManager.persist(buildStock());
        Long entryId = testEntityManager.persistAndGetId(buildStockEntry(stock, 1l, 2, 1.2), Long.class);
        StockEntry entry = stockEntryRepository.findOne(entryId);

        assertThat(entry.getQuantity(), is(2l));
        assertThat(entry.getPrice(), is(BigDecimal.valueOf(1.2)));
        assertThat(entry.getProductId(), is(1l));
    }

    @Test
    public void testStockQuantities() throws Exception {
        Long stockId = persistStockEntries();

        List<StockEntryQuantity> quantities = stockEntryRepository.findAvailableProductQuantities();

        assertThat(quantities, hasSize(2));
        assertThat(quantities, containsInAnyOrder(new StockEntryQuantity(1l, 3l), new StockEntryQuantity(2l, 7l)));
    }

    @Test
    public void testFindByStockIdAndProductId() throws Exception {
        Long stockId = persistStockEntries();

        List<StockEntry> entries = stockEntryRepository.findByStockIdAndProductId(stockId, 1l);

        assertThat(entries, hasSize(2));

        assertThat(entries.get(0).getPrice(), is(equalTo(BigDecimal.valueOf(1.1))));
        assertThat(entries.get(1).getPrice(), is(equalTo(BigDecimal.valueOf(1.2))));
    }

    private Long persistStockEntries() {
        Stock stock = testEntityManager.persist(new Stock().setName("STOCK_NAME"));

        StockEntry[] entries = {
                buildStockEntry(stock, 1l, 1, 1.1),
                buildStockEntry(stock, 1l, 2, 1.2),
                buildStockEntry(stock, 2l, 3, 1.3),
                buildStockEntry(stock, 2l, 4, 1.4),
                buildStockEntry(stock, 3l, 0, 1.5),
                buildStockEntry(stock, 3l, 0, 1.6)
        };

        Arrays.stream(entries).forEach(testEntityManager::persist);

        return stock.getId();
    }
}