package com.mfirmanakbar.dans.service;

import com.mfirmanakbar.dans.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.mfirmanakbar.dans.model.User user = userRepository.findByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public String encodeUserPassword(String userPassword) {
        return bcryptEncoder.encode(userPassword);
    }

}