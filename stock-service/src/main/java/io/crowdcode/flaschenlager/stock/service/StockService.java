package io.crowdcode.flaschenlager.stock.service;

import io.crowdcode.flaschenlager.stock.model.Stock;
import io.crowdcode.flaschenlager.stock.model.StockEntry;
import io.crowdcode.flaschenlager.stock.model.StockEntryQuantity;
import io.crowdcode.flaschenlager.stock.model.StockEntryResponse;
import io.crowdcode.flaschenlager.stock.repository.StockEntryRepository;
import io.crowdcode.flaschenlager.stock.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockEntryRepository stockEntryRepository;

    public Long registerStock(String stockName) {
        return stockRepository.save(new Stock().setName(stockName)).getId();
    }

    public List<Stock> findAllStocks() {
        return stockRepository.findAll();
    }

    public Stock findStock(Long stockId) {
        return stockRepository.findOne(stockId);
    }

    public List<StockEntryQuantity> quantityOfAvailableProducts(Long stockId) {
        return stockEntryRepository.findAvailableProductQuantities(stockId);
    }

    public void put(Long stockId, Long productId, Long quantity, double price) {
        Stock stock = stockRepository.findOne(stockId);

        StockEntry entry = new StockEntry()
                .setStock(stock)
                .setAddedAt(LocalDateTime.now())
                .setProductId(productId)
                .setQuantity(quantity)
                .setPrice(BigDecimal.valueOf(price));

        stockEntryRepository.save(entry);
    }

    public List<StockEntryResponse> pull(Long stockId, Long productId, Long quantity) {
        List<StockEntry> availableEntries = stockEntryRepository.findByStockIdAndProductId(stockId, productId);

        List<StockEntryResponse> entries = new ArrayList<>();

        pullQuantityFromEntries(quantity, availableEntries, entries);

        return entries;
    }

    private void pullQuantityFromEntries(Long quantity, List<StockEntry> availableEntries, List<StockEntryResponse> entries) {
        Long remains = quantity;
        for (StockEntry entry : availableEntries) {
            if (remains <= 0) {
                break;
            }
            long taking = Math.min(entry.getQuantity(), remains);

            entries.add(new StockEntryResponse()
                    .setProductId(entry.getProductId())
                    .setPrice(entry.getPrice())
                    .setQuantity(taking));

            entry.setQuantity(entry.getQuantity() - taking);

            remains -= taking;
        }
    }


}
