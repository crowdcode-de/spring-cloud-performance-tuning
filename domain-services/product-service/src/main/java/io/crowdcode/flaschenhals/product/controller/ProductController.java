package io.crowdcode.flaschenhals.product.controller;

import io.crowdcode.flaschenhals.product.model.Product;
import io.crowdcode.flaschenhals.product.repository.ProductRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ApiOperation(value = "Find one product")
    @GetMapping(value = "/{productId}")
    public Product findOne(@PathVariable("productId") Long productId) {
        return productRepository.findOne(productId);
    }

    @ApiOperation(value = "Create Product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createProduct(@Valid @RequestBody Product product) {
        productRepository.save(product);
    }

    @ApiOperation(value = "Update Product")
    @PutMapping(value = "/{productId}")
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

    @ApiOperation(value = "Search Products by Tag.")
    @GetMapping(params = "tags")
    public List<Product> search(@RequestParam("tags") String[] tags) {
        return productRepository.findByTag(tags);
    }

}
