package com.houseproject.house.service;


import com.houseproject.house.dto.LoginResponseDto;
import com.houseproject.house.models.Role;
import com.houseproject.house.models.User;
import com.houseproject.house.repository.RoleRepository;
import com.houseproject.house.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


//    @Autowired
//    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }


    public User registerUser(String name, String username, String password){

        String encodePassword = passwordEncoder.encode(password);
        Role role = roleRepository.findByAuthority("USER")
                .orElseThrow(() -> new RuntimeException("Role not found")); // Throw an exception if the role is not found

        Set<Role> authorities = new HashSet<>();

        authorities.add(role);

        return userRepository.save(new User(name,username,encodePassword,authorities));
    }

    public LoginResponseDto loginUser(String username, String password){

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

            Authentication auth = authenticationManager.authenticate( usernamePasswordAuthenticationToken);

            String token = tokenService.generateJwt(auth);
            User user = userRepository.findByUsername(username).get();
            return new LoginResponseDto(user, token);
        }catch (AuthenticationException e){
            System.out.println(e.getMessage());
            log.info(e.getMessage());
            return new LoginResponseDto(null, "");
        }
    }

}
