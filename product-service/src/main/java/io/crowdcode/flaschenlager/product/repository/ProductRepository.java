package io.crowdcode.flaschenlager.product.repository;

import io.crowdcode.flaschenlager.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p LEFT JOIN p.tags t WHERE t in :tags")
    List<Product> findByTag(@Param("tags") String... tags);
}
