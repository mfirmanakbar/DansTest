package com.mfirmanakbar.dans.validator;

import com.mfirmanakbar.dans.request.UserRequest;
import com.mfirmanakbar.dans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    @Autowired
    private UserService userService;

    public List<String> validateSuccess(UserRequest userRequest) {
        ArrayList<String> errors = new ArrayList<>();

        if (userRequest.getUsername() == null || userRequest.getUsername().isEmpty()) {
            errors.add("Username must be fill");
        }
        if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
            errors.add("Password must be fill");
        }
        if (userService.findByUsername(userRequest.getUsername()).getStatusCode() == HttpStatus.OK) {
            errors.add("Username already exist or registered");
        }

        return errors;
    }
}
