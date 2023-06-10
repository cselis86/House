package com.houseproject.house.controller;

import com.houseproject.house.dto.NewUserRequest;
import com.houseproject.house.models.User;
import com.houseproject.house.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house/api")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello";
    }

//    @GetMapping("/user")
//    public List<User> getUsers(){
//        return userService.findAll();
//    }

//    @PostMapping
//    public User newUser(@RequestBody NewUserRequest userRequest){
//        User user = new User();
//
//        user.setUsername(userRequest.email());
//        user.setUsername(userRequest.name());
//
//        return userService.saveUser(user);
//    }

}
