package io.crowdcode.flaschenlager.stock.repository;

import io.crowdcode.flaschenlager.stock.model.StockEntry;
import io.crowdcode.flaschenlager.stock.model.StockEntryQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockEntryRepository extends JpaRepository<StockEntry, Long> {

    @Query("SELECT new io.crowdcode.flaschenlager.stock.model.StockEntryQuantity(e.productId, SUM(e.quantity)) " +
            "FROM StockEntry e WHERE e.quantity >0 GROUP BY e.productId")
    List<StockEntryQuantity> findStockQuantities();
}
