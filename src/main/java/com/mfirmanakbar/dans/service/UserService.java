package com.mfirmanakbar.dans.service;

import com.mfirmanakbar.dans.request.JwtRequest;
import com.mfirmanakbar.dans.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> save(UserRequest userRequest);

    ResponseEntity<?> login(JwtRequest authenticationRequest) throws Exception;

    ResponseEntity<?> findByUsername(String Username);
}
