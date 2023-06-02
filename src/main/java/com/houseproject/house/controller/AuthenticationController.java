package com.houseproject.house.controller;

import com.houseproject.house.dto.LoginDto;
import com.houseproject.house.dto.LoginResponseDto;
import com.houseproject.house.dto.RegistrationDto;
import com.houseproject.house.models.User;
import com.houseproject.house.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("house/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDto dto){
        return authenticationService.registerUser(dto.name(), dto.username(), dto.password());
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody LoginDto dto){
        return authenticationService.loginUser(dto.username(), dto.password());
    }
}
