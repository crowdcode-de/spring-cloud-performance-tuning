package io.crowdcode.flaschenlager.stock.service;


import io.crowdcode.flaschenlager.stock.model.StockEntryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StockServicePullTest {

    public static final String STOCK = "STOCK_NAME_BY_SERVICE";

    @Autowired
    private StockService stockService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testPullEntriesFromExistingStock() throws Exception {
        Long stockId = stockService.registerStock(STOCK);
        stockService.put(stockId, 1l, 0l, 1.1);
        stockService.put(stockId, 1l, 2l, 1.2);
        stockService.put(stockId, 1l, 3l, 1.3);
        stockService.put(stockId, 1l, 4l, 1.4);
        stockService.put(stockId, 1l, 5l, 1.5);

        entityManager.flush();
        entityManager.clear();

        List<StockEntryResponse> pull = stockService.pull(1l, 1l, 6l);

        entityManager.flush();

        assertThat(pull, hasSize(3));
        assertThat(pull, containsInAnyOrder(
                new StockEntryResponse(1l, 2l, new BigDecimal("1.20")),
                new StockEntryResponse(1l, 3l, new BigDecimal("1.30")),
                new StockEntryResponse(1l, 1l, new BigDecimal("1.40"))));

    }
}