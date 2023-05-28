package com.houseproject.house.service;


import com.houseproject.house.models.Role;
import com.houseproject.house.models.User;
import com.houseproject.house.repository.RoleRepository;
import com.houseproject.house.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


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

}
