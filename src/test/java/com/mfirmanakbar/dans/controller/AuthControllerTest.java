package com.mfirmanakbar.dans.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfirmanakbar.dans.request.JwtRequest;
import com.mfirmanakbar.dans.request.UserRequest;
import com.mfirmanakbar.dans.response.CommonResponse;
import com.mfirmanakbar.dans.response.JwtResponse;
import com.mfirmanakbar.dans.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @InjectMocks
    private AuthController authController;

    private String expectedToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJha2JhciIsImV4cCI6MTU5MDE0MDM5MSwiaWF0IjoxNTkwMTIyMzkxfQ.6dx21V4OFTa1jvxt-_2b-qEguADgMuG9D7pOi0uiWiR7TC_jo97QaNBNH7I3SolXTu9UhCcIq8jq2OdZHhiwkw";

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void register() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .username("firman")
                .password("12345678")
                .build();

        ResponseEntity loginResponseEntity = new ResponseEntity<>(new CommonResponse("Register successfully"), HttpStatus.CREATED);
        when(userService.save(userRequest)).thenReturn(loginResponseEntity);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        JwtRequest jwtRequest = JwtRequest.builder()
                .username("firman")
                .password("12345678")
                .build();

        ResponseEntity loginResponseEntity = ResponseEntity.ok(new JwtResponse(expectedToken));
        when(userService.login(jwtRequest)).thenReturn(loginResponseEntity);

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jwtRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
