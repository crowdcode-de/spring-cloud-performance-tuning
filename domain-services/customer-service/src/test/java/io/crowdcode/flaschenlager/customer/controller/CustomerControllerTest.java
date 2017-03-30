package io.crowdcode.flaschenlager.customer.controller;

import io.crowdcode.flaschenlager.customer.fixture.CustomerFixture;
import io.crowdcode.flaschenlager.customer.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    public static final String CUSTOMER_JSON = "{\"id\":null,\"version\":null,\"name\":\"NAME\",\"address\":\"ADDRESS\",\"phone\":\"PHONE\",\"email\":\"EM@A.IL\",\"employees\":[{\"userId\":1},{\"userId\":2}]}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void testCreateCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/customer").content(CUSTOMER_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }


    @Test
    public void testFindCustomer() throws Exception {
        when(customerRepository.findOne(anyLong()))
                .thenReturn(CustomerFixture.buildDefaultCustomer());

        mvc.perform(MockMvcRequestBuilders.get("/customer/{customerId}", 5l)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content()
                        .json(CUSTOMER_JSON));

    }

    @Test
    public void testFindAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content()
                        .json("[]"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        when(customerRepository.findOne(anyLong()))
                .thenReturn(CustomerFixture.buildDefaultCustomer());

        mvc.perform(MockMvcRequestBuilders.put("/customer/{customerId}", 5l).content(CUSTOMER_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}
