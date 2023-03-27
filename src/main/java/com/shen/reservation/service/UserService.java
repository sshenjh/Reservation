package com.shen.reservation.service;

import com.shen.reservation.dto.UserRegistrationDto;
import com.shen.reservation.model.SecurityUser;
import com.shen.reservation.model.User;
import com.shen.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

}
