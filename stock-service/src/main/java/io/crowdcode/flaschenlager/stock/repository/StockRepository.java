package io.crowdcode.flaschenlager.stock.repository;

import io.crowdcode.flaschenlager.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {


}
