package com.tierup.tierup.auth.controller;

import com.tierup.tierup.auth.dto.UserInfoResponse;
import com.tierup.tierup.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @GetMapping ("/callback")
    public ResponseEntity<String> getAccessToken(@RequestParam String code) {
        return ResponseEntity.ok(authService.getAccessToken(code));
    }

    @GetMapping("/user/info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@RequestHeader HttpHeaders headers) {
        String accessToken = headers.getFirst("Authorization");
        return ResponseEntity.ok(authService.getGithubUserInfo(accessToken));
    }
}
