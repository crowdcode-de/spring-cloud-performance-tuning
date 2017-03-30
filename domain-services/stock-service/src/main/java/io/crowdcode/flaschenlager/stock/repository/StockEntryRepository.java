package io.crowdcode.flaschenlager.stock.repository;

import io.crowdcode.flaschenlager.stock.model.StockEntry;
import io.crowdcode.flaschenlager.stock.resource.StockEntryQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockEntryRepository extends JpaRepository<StockEntry, Long> {

    @Query("SELECT new io.crowdcode.flaschenlager.stock.resource.StockEntryQuantity(e.productId, SUM(e.quantity)) " +
            "FROM StockEntry e LEFT JOIN e.stock s WHERE s.id = :stockId AND e.quantity >0 GROUP BY e.productId")
    List<StockEntryQuantity> findAvailableProductQuantities(@Param("stockId") Long stockId);


    @Query("SELECT e FROM StockEntry e LEFT JOIN e.stock s " +
            "WHERE s.id = :stockId AND e.productId = :productId AND e.quantity > 0 ORDER BY e.addedAt ASC")
    List<StockEntry> findByStockIdAndProductId(@Param("stockId") Long stockId, @Param("productId") Long productId);
}
