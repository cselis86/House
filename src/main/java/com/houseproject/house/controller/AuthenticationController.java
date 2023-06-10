package com.houseproject.house.controller;

import com.houseproject.house.dto.AuthenticationRequest;
import com.houseproject.house.dto.AuthenticationResponse;
import com.houseproject.house.dto.RegisterRequest;
import com.houseproject.house.dto.RegistrationDto;
import com.houseproject.house.models.User;
import com.houseproject.house.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/house/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
//
//    @PostMapping("/register")
//    public User registerUser(@RequestBody RegistrationDto dto){
//        return authenticationService.registerUser(dto.name(), dto.username(), dto.password());
//    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
