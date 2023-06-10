package com.houseproject.house.service;


import com.houseproject.house.dto.AuthenticationRequest;
import com.houseproject.house.dto.AuthenticationResponse;
import com.houseproject.house.dto.RegisterRequest;
import com.houseproject.house.models.Role;
import com.houseproject.house.models.User;
import com.houseproject.house.repository.RoleRepository;
import com.houseproject.house.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {

        HashSet<Role> roles = new HashSet<>();
//        Role role = roleRepository.findById(1).orElse(null);
        Role role = new Role("USER");

        roleRepository.save(role);
        roles.add(role);


        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(roles)
                .build();

        repository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails user = repository.findByUsername(request.getUsername()).orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
