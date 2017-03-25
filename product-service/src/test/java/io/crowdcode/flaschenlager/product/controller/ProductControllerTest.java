package io.crowdcode.flaschenlager.product.controller;


import io.crowdcode.flaschenlager.product.model.Product;
import io.crowdcode.flaschenlager.product.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private static final String PRODUCT_JSON = "{ \"amount\": 0, \"description\": \"string\", \"name\": \"string\", \"unit\": \"string\", \"version\": 0}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;


    @Test
    public void testCreateProduct() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/product").content(PRODUCT_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        verify(productRepository, times(1)).save(any(Product.class));
    }


}