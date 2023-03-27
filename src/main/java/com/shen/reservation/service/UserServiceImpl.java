package com.shen.reservation.service;

import com.shen.reservation.dto.UserRegistrationDto;
import com.shen.reservation.model.Flight;
import com.shen.reservation.model.SecurityUser;
import com.shen.reservation.model.User;
import com.shen.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUsername: "+username);
        return userRepository.findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("Username not found: " + username));
    }

    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUsername(),
                passwordEncoder.encode(registrationDto.getPassword()),
                registrationDto.getEmail(),
                "ROLE_USER");

        return userRepository.save(user);
    }


    public User save(User user) {
        return userRepository.save(user);
    }


    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
