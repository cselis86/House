package com.houseproject.house.service;

import com.houseproject.house.models.Role;
import com.houseproject.house.models.User;
import com.houseproject.house.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User deleteUserById(Long id){
        Optional<User> user = userRepository.findById(id);

        if (user.get() != null){
            userRepository.deleteById(id);
        }

        return user.orElse(null);
    }

    public List<User> findAll(){

        return userRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the user details service");

//        if (!username.equals("Elis")) {
//            throw new UsernameNotFoundException("Not Elis");
//        }
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(1,"USER"));
//
//
//        return new User(1L,"Elis","Elis",encoder.encode("password"),roles);

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
