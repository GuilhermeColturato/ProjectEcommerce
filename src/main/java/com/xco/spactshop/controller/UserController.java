package com.xco.spactshop.controller;

import com.xco.spactshop.model.User;
import com.xco.spactshop.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        // INSEGURO: A senha não está sendo codificada
        return userService.save(user);
    }
}
