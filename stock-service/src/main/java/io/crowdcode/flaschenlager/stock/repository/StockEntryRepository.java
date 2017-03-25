package io.crowdcode.flaschenlager.stock.repository;

import io.crowdcode.flaschenlager.stock.model.StockEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockEntryRepository extends JpaRepository<StockEntry, Long> {

}
