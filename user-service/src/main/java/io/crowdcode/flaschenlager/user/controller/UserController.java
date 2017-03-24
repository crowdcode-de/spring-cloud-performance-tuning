package io.crowdcode.flaschenlager.user.controller;

import io.crowdcode.flaschenlager.user.dto.PasswordRequest;
import io.crowdcode.flaschenlager.user.model.User;
import io.crowdcode.flaschenlager.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Create a new user.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody User user) {
        userRepository.save(user);
    }

    @ApiOperation(value = "Find all users.")
    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Change password.")
    @PutMapping("/{userId}/change-password")
    public void updatePassword(Long userId, @RequestBody PasswordRequest passwordRequest) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            if (user.getPassword() == passwordRequest.getOldPassword()) {
                user.setPassword(passwordRequest.getNewPassword());
                userRepository.save(user);
            }
        }
    }


}
