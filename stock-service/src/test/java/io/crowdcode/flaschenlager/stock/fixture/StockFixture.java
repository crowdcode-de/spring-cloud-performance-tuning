package io.crowdcode.flaschenlager.stock.fixture;

import io.crowdcode.flaschenlager.stock.model.Stock;
import io.crowdcode.flaschenlager.stock.model.StockEntry;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by idueppe on 25.03.17.
 */
public class StockFixture {

    private static long now = System.currentTimeMillis();
    private static int nanoOfSecond = 0;

    public static Stock buildStock() {
        return new Stock().setName("STOCK_NAME");
    }

    public static Stock buildPersistentStock() {
        return buildStock().setId(1l).setVersion(1l);
    }

    public static StockEntry buildStockEntry(Stock stock, long productId, long quantity, double price) {
        return buildStockEntry(productId, quantity, price).setStock(stock);
    }

    public static StockEntry buildStockEntry(long productId, long quantity, double price) {
        return new StockEntry()
                .setAddedAt(now())
                .setPrice(BigDecimal.valueOf(price))
                .setQuantity(quantity)
                .setProductId(productId);
    }

    private static LocalDateTime now() {
        return LocalDateTime.ofEpochSecond(now, nanoOfSecond++, ZoneOffset.UTC);
    }
}
