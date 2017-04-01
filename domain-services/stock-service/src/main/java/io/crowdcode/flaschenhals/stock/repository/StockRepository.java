package io.crowdcode.flaschenhals.stock.repository;

import io.crowdcode.flaschenhals.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {


}
