package io.crowdcode.flaschenlager.stock.controller;

import io.crowdcode.flaschenlager.stock.fixture.StockFixture;
import io.crowdcode.flaschenlager.stock.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static io.crowdcode.flaschenlager.stock.fixture.StockFixture.buildListStockEntryResponse;
import static io.crowdcode.flaschenlager.stock.fixture.StockFixture.buildPersistentStock;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StockController.class)
public class StockControllerTest {

    private static final String STOCK_JSON = "{ \"name\": \"H7\"}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StockService stockService;


    @Test
    public void testRegisterStock() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/stock").content(STOCK_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        verify(stockService, times(1)).registerStock("H7");
    }


    @Test
    public void testListStocks() throws Exception {
        when(stockService.findAllStocks()).thenReturn(Collections.singletonList(buildPersistentStock()));

        mvc.perform(MockMvcRequestBuilders.get("/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"stockId\":1,\"name\":\"STOCK_NAME\"}]"));
    }

    @Test
    public void testGetStock() throws Exception {
        when(stockService.findStock(1l)).thenReturn(buildPersistentStock());

        mvc.perform(MockMvcRequestBuilders.get("/stock/{stockId}", 1l)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"stockId\":1,\"name\":\"STOCK_NAME\"}s"));

        verify(stockService, times(1)).findStock(1l);
    }


    @Test
    public void testGetStockEntries() throws Exception {
        when(stockService.quantityOfAvailableProducts(1l)).thenReturn(StockFixture.buildQuantityOfAvailableProducts());

        mvc.perform(MockMvcRequestBuilders.get("/stock/{stockId}/entries", 1l)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"productId\":1,\"quantity\":24},{\"productId\":2,\"quantity\":12},{\"productId\":3,\"quantity\":6}]"));

        verify(stockService, times(1)).quantityOfAvailableProducts(1l);
    }


    @Test
    public void testPutEntriesToStock() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/stock/{stockId}/entries", 1l)
                .content("{\"productId\":1,\"quantity\":12,\"price\":1.20}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(stockService, times(1)).put(1l, 1l, 12l, 1.20);
    }

    @Test
    public void testPullEntriesFromStock() throws Exception {
        when(stockService.pull(1l, 1l, 12l)).thenReturn(buildListStockEntryResponse());
        mvc.perform(MockMvcRequestBuilders.post("/stock/{stockId}/entries", 1l)
                .content("{\"productId\":1,\"quantity\":12}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"productId\":1,\"quantity\":10,\"price\":1.10},{\"productId\":1,\"quantity\":10,\"price\":1.20}]"));
        verify(stockService, times(1)).pull(1l, 1l, 12l);
    }
}