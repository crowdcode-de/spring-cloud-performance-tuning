package io.crowdcode.flaschenhals.product.controller;


import io.crowdcode.flaschenhals.product.model.Product;
import io.crowdcode.flaschenhals.product.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static io.crowdcode.flaschenhals.product.fixture.ProductFixture.buildDefaultProduct;
import static io.crowdcode.flaschenhals.product.fixture.ProductFixture.buildPersistentProduct;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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


    @Test
    public void testUpdateProduct() throws Exception {
        when(productRepository.findOne(1L)).thenReturn(buildPersistentProduct());

        mvc.perform(MockMvcRequestBuilders.put("/product/{productId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testFindAll() throws Exception {
        when(productRepository.findAll()).thenReturn(Arrays.asList(buildDefaultProduct(), buildPersistentProduct()));

        mvc.perform(MockMvcRequestBuilders.get("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"id\":null,\"version\":null,\"name\":\"PRODUCT_NAME\",\"description\":\"PRODUCT_DESCRIPTION\",\"unit\":\"Liter\",\"amount\":1.0,\"tags\":[]},{\"id\":-2,\"version\":null,\"name\":\"PRODUCT_NAME_2\",\"description\":\"PRODUCT_DESCRIPTION_2\",\"unit\":\"1L\",\"amount\":123.45,\"tags\":[]}]"));
    }

    @Test
    public void testFindOne() throws Exception {
        when(productRepository.findOne(1L)).thenReturn(buildPersistentProduct());

        mvc.perform(MockMvcRequestBuilders.get("/product/{productId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{}"));
    }

    @Test
    public void testFindByTags() throws Exception {
        when(productRepository.findByTag("BIER", "COLA")).thenReturn(Collections.singletonList
                (buildDefaultProduct().addTags("BIER")));

        mvc.perform(MockMvcRequestBuilders.get("/product?tags=BIER,COLA")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"id\":null,\"version\":null,\"name\":\"PRODUCT_NAME\",\"description\":\"PRODUCT_DESCRIPTION\",\"unit\":\"Liter\",\"amount\":1.0,\"tags\":[\"BIER\"]}]"));

        verify(productRepository, times(1)).findByTag("BIER", "COLA");

    }
}