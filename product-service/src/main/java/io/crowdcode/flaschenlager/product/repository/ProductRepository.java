package io.crowdcode.flaschenlager.product.repository;

import io.crowdcode.flaschenlager.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
