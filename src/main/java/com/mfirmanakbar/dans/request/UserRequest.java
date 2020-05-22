package com.mfirmanakbar.dans.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String username;
    private String password;

    public UserRequest() {
    }

    @Builder
    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
