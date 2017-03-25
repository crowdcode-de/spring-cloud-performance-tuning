package io.crowdcode.flaschenlager.stock.service;


import io.crowdcode.flaschenlager.stock.model.Stock;
import io.crowdcode.flaschenlager.stock.model.StockEntryQuantity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StockServiceTest {

    private static final String STOCKNAME = "STOCK_NAME_BY_SERVICE";

    @Autowired
    private StockService stockService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testRegisterStock() throws Exception {
        Long stockId = stockService.registerStock(STOCKNAME);
        assertThat(stockId, is(notNullValue()));
    }

    @Test
    public void testFindAllStock() throws Exception {
        Long stockId = stockService.registerStock(STOCKNAME);
        List<Stock> stocks = stockService.findAllStocks();

        assertThat(stocks, hasSize(1));
        assertThat(stocks, contains(new Stock().setId(stockId).setVersion(0l).setName(STOCKNAME)));
    }

    @Test
    public void testFindStock() throws Exception {
        Long stockId = stockService.registerStock(STOCKNAME);
        Stock stock = stockService.findStock(stockId);

        assertThat(stock.getName(), is(STOCKNAME));
    }

    @Test
    public void testStockQuantities() throws Exception {
        Long stockId = stockService.registerStock(STOCKNAME);
        stockService.put(stockId, 1l, 1l, 1.1);
        stockService.put(stockId, 1l, 2l, 1.2);
        stockService.put(stockId, 2l, 3l, 1.3);
        stockService.put(stockId, 2l, 4l, 1.4);
        stockService.put(stockId, 3l, 0l, 1.5);
        stockService.put(stockId, 3l, 0l, 1.6);

        List<StockEntryQuantity> quantities = stockService.quantityOfAvailableProducts(stockId);

        assertThat(quantities, hasSize(2));
        assertThat(quantities, containsInAnyOrder(new StockEntryQuantity(1l, 3l), new StockEntryQuantity(2l, 7l)));
    }
}