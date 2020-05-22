package com.mfirmanakbar.dans.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {

    private String message;

    @Builder
    public CommonResponse(String message) {
        this.message = message;
    }

}
