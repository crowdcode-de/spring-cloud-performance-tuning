package io.crowdcode.flaschenlager.product.controller;

import io.crowdcode.flaschenlager.product.model.Product;
import io.crowdcode.flaschenlager.product.repository.ProductRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @ApiOperation(value = "Find all products.")
    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @ApiOperation(value = "Create Product")
    @PostMapping
    public void createProduct(@Valid @RequestBody Product product) {
        productRepository.save(product);
    }

    @ApiOperation(value = "Update Product")
    @PutMapping
    public void updateProduct(@PathVariable("productId") Long productId, @Valid @RequestBody Product product) {
        Product entity = productRepository.findOne(productId);
        if (entity != null) {
            entity.setName(product.getName());
            entity.setDescription(product.getDescription());
            entity.setUnit(product.getUnit());
            entity.setAmount(product.getAmount());
            productRepository.save(entity);
        }
    }
}
