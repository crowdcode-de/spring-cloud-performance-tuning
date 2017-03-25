package io.crowdcode.flaschenlager.stock.service;

import io.crowdcode.flaschenlager.stock.model.Stock;
import io.crowdcode.flaschenlager.stock.model.StockEntry;
import io.crowdcode.flaschenlager.stock.model.StockEntryQuantity;
import io.crowdcode.flaschenlager.stock.repository.StockEntryRepository;
import io.crowdcode.flaschenlager.stock.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public List<StockEntryQuantity> quantityOfAvailableProducts() {
        return stockEntryRepository.findAvailableProductQuantities();
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
}
