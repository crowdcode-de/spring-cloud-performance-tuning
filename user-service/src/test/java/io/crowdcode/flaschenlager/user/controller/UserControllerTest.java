package io.crowdcode.flaschenlager.user.controller;

import io.crowdcode.flaschenlager.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    public static final String USER_JSON = "{  \"email\": \"email_value\",  \"name\": \"name_value\",  \"password\": \"password_value\"}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void testCreateCustomer() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/user").content(USER_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }


}