package io.crowdcode.flaschenlager.stock.service;

import io.crowdcode.flaschenlager.stock.model.Stock;
import io.crowdcode.flaschenlager.stock.repository.StockEntryRepository;
import io.crowdcode.flaschenlager.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockEntryRepository stockEntryRepository;

    public Stock registerStock(String stockName) {
        return stockRepository.save(new Stock().setName(stockName));
    }
}
