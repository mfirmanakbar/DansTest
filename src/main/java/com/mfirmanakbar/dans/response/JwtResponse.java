package com.mfirmanakbar.dans.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    @JsonProperty("token")
    private final String jwtToken;

    @Builder
    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}