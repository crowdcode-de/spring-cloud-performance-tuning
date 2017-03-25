package io.crowdcode.flaschenlager.stock.fixture;

import io.crowdcode.flaschenlager.stock.model.Stock;
import io.crowdcode.flaschenlager.stock.model.StockEntry;
import io.crowdcode.flaschenlager.stock.model.StockEntryQuantity;
import io.crowdcode.flaschenlager.stock.model.StockEntryResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

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

    public static List<StockEntryQuantity> buildQuantityOfAvailableProducts() {
        return Arrays.asList(
                new StockEntryQuantity(1l, 24l),
                new StockEntryQuantity(2l, 12l),
                new StockEntryQuantity(3l, 6l)
        );
    }

    public static List<StockEntryResponse> buildListStockEntryResponse() {
        return Arrays.asList(
                new StockEntryResponse(1l, 10l, new BigDecimal("1.10")),
                new StockEntryResponse(1l, 10l, new BigDecimal("1.20"))
        );
    }

    private static LocalDateTime now() {
        return LocalDateTime.ofEpochSecond(now, nanoOfSecond++, ZoneOffset.UTC);
    }
}
