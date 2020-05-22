package com.mfirmanakbar.dans.service;

import com.mfirmanakbar.dans.model.User;
import com.mfirmanakbar.dans.repository.UserRepository;
import com.mfirmanakbar.dans.request.JwtRequest;
import com.mfirmanakbar.dans.request.UserRequest;
import com.mfirmanakbar.dans.response.CommonResponse;
import com.mfirmanakbar.dans.response.JwtResponse;
import com.mfirmanakbar.dans.security.JwtTokenUtil;
import com.mfirmanakbar.dans.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidator userValidator;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<?> save(UserRequest userRequest) {
        List<String> errors = userValidator.validateSuccess(userRequest);
        if (errors.size() > 0) {
            return new ResponseEntity<>(new CommonResponse(errors.toString()), HttpStatus.BAD_REQUEST);
        } else {
            userRequest.setPassword(jwtUserDetailsService.encodeUserPassword(userRequest.getPassword()));
            User user = User.builder()
                    .username(userRequest.getUsername())
                    .password(userRequest.getPassword())
                    .build();
            User userResp = userRepository.save(user);
            if (userResp.getId() > 0) {
                return new ResponseEntity<>(new CommonResponse("Register successfully"), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(new CommonResponse("internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> login(JwtRequest authenticationRequest) throws Exception {
        String authenticate = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        if (authenticate != null) {
            return new ResponseEntity<>(new CommonResponse("Usenrame or Password Invalid"), HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (userDetails == null) {
            return new ResponseEntity<>(new CommonResponse("Usenrame or Password wrong"), HttpStatus.UNAUTHORIZED);
        }

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private String authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return null;
        } catch (DisabledException e) {
            return "USER_DISABLED " + e.getMessage();
        } catch (BadCredentialsException e) {
            return "INVALID_CREDENTIALS " + e.getMessage();
        }
    }

    @Override
    public ResponseEntity<?> findByUsername(String Username) {
        User user;
        user = userRepository.findByUsername(Username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CommonResponse("Username not found"), HttpStatus.NOT_FOUND);
    }
}
