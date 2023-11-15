package com.tierup.tierup.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserInfoResponse {

    @JsonProperty("login")
    private String account;

    @JsonProperty("name")
    private String username;

    @JsonProperty("avatar_url")
    private String imgUrl;
}
