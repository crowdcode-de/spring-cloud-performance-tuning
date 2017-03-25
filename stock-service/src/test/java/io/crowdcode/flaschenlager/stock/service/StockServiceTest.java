package io.crowdcode.flaschenlager.stock.service;


import io.crowdcode.flaschenlager.stock.model.StockEntryQuantity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StockServiceTest {

    public static final String STOCK = "STOCK_NAME_BY_SERVICE";
    @Autowired
    private StockService stockService;

    @Test
    public void testRegisterStock() throws Exception {
        Long stockId = stockService.registerStock("STOCK_NAME_BY_SERVICE");
        assertThat(stockId, is(notNullValue()));
    }

    @Test
    public void testStockQuantities() throws Exception {
        Long stockId = stockService.registerStock(STOCK);
        stockService.put(stockId, 1l, 1l, 1.1);
        stockService.put(stockId, 1l, 2l, 1.2);
        stockService.put(stockId, 2l, 3l, 1.3);
        stockService.put(stockId, 2l, 4l, 1.4);
        stockService.put(stockId, 3l, 0l, 1.5);
        stockService.put(stockId, 3l, 0l, 1.6);

        List<StockEntryQuantity> quantities = stockService.calculateQuantities();

        assertThat(quantities, hasSize(2));
        assertThat(quantities, containsInAnyOrder(new StockEntryQuantity(1l, 3l), new StockEntryQuantity(2l, 7l)));
    }
}