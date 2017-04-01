package io.crowdcode.flaschenhals.user.controller;

import io.crowdcode.flaschenhals.user.fixture.UserFixture;
import io.crowdcode.flaschenhals.user.model.User;
import io.crowdcode.flaschenhals.user.repository.UserRepository;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String USER_JSON = "{  \"email\": \"email_value\",  \"name\": \"name_value\",  \"password\": \"password_value\"}";

    private static final String PASSWORD_JSON = "{\"oldPassword\":\"secret2\",\"newPassword\":\"ZYX\"}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void testCreateUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/user").content(USER_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }


    @Test
    public void testUpdatePassword() throws Exception {
        User user = UserFixture.buildPersistentUser();

        when(userRepository.findOne(2L))
                .thenReturn(user);

        mvc.perform(MockMvcRequestBuilders.put("/user/{userId}/change-password", 2L).content(PASSWORD_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

        user.setPassword("ZYX");

        verify(userRepository, times(1))
                .save(user);
    }

    @Test
    public void testFindCustomer() throws Exception {
        when(userRepository.findAll())
                .thenReturn(Arrays.asList(UserFixture.buildDefaultUser(), UserFixture.buildPersistentUser()));

        mvc.perform(MockMvcRequestBuilders.get("/user", 5l)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())

                .andExpect(content()
                        .json(" [{\"id\":null,\"email\":\"junit@crowdcode.io\",\"name\":\"Name\"},{\"id\":-2,\"email\":\"junit2@crowdcode.io\",\"name\":\"Name2\"}]"));
    }
}