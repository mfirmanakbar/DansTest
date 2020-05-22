package com.mfirmanakbar.dans.controller;

import com.mfirmanakbar.dans.request.JwtRequest;
import com.mfirmanakbar.dans.request.UserRequest;
import com.mfirmanakbar.dans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return userService.login(authenticationRequest);
    }

}
