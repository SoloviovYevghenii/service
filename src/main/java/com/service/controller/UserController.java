package com.service.controller;

import com.service.dto.UserDto;
import com.service.dto.UserSaveRequest;
import com.service.entity.ColorArticle;
import com.service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // находим всех User у кого возраст > age
    @GetMapping("/getUserByAge")
    public List<UserDto> getUserByAge(Integer age) {
        return userService.getUserByAge(age);
    }

    @PostMapping("/getUserByColorArticle")
    public List<UserDto> getUserByColorArticle(@RequestParam ColorArticle color) {
        return userService.getUserByColorArticle(color);
    }

    @GetMapping("/getUserByCount")
    public List<String> getUserByCount(int count) {
        return userService.getUserByCount(count);
    }

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody UserSaveRequest userSaveRequest) {
        userService.saveUser(userSaveRequest);
    }
}