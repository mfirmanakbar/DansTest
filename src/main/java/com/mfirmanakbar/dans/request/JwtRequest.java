package com.mfirmanakbar.dans.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    public JwtRequest() {
    }

    @Builder
    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}