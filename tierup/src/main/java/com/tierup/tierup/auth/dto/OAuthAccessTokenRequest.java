package com.tierup.tierup.auth.dto;

import lombok.Getter;

@Getter
public class OAuthAccessTokenRequest {

    private String clientId;
    private String clientSecret;
    private String code;

    public OAuthAccessTokenRequest(String clientId, String clientSecret, String code) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
    }
}
