package com.houseproject.house.controller;

import com.houseproject.house.dto.NewUserRequest;
import com.houseproject.house.models.User;
import com.houseproject.house.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/house")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello";
    }

}
