package io.crowdcode.flaschenlager.stock.service;


import io.crowdcode.flaschenlager.stock.model.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    public void testRegisterStock() throws Exception {
        Stock stock = stockService.registerStock("STOCK_NAME_BY_SERVICE");
        assertThat(stock.getId(), is(notNullValue()));
    }
}