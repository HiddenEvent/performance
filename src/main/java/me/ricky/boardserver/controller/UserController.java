package me.ricky.boardserver.controller;

import me.ricky.boardserver.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.BASE_URI)
public class UserController {
    public static final String BASE_URI = "/users";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
