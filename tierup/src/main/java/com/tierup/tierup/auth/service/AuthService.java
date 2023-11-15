package com.tierup.tierup.auth.service;

import com.tierup.tierup.auth.dto.*;
import com.tierup.tierup.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    @Value("${oauth2.user.github.client-id}")
    private String clientId;

    @Value("${oauth2.user.github.redirect-url}")
    private String redirectUrl;

    @Value("${oauth2.user.github.login-url}")
    private String loginUrl;

    @Value("${oauth2.user.github.token-url}")
    private String tokenUrl;

    @Value("${oauth2.user.github.client-secret}")
    private String clientSecret;

    @Value("${GITHUB_USER_URL}")
    private String userUrl;

    private String state = UUID.randomUUID().toString();

    public RedirectView requestCode(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("client_id", clientId)
                .addAttribute("redirect_url", redirectUrl)
                .addAttribute("state", state);

        log.debug("requestCode");
        return new RedirectView(loginUrl);
    }

    public String getAccessToken(String code) {
        Map<String, String> bodies = new HashMap<>();
        bodies.put("client_id", clientId);
        bodies.put("client_secret", clientSecret);
        bodies.put("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Object> request = new HttpEntity<>(bodies, headers);
        ResponseEntity<OAuthAccessTokenResponse> response = new RestTemplate().postForEntity(tokenUrl, request, OAuthAccessTokenResponse.class);

        return response.getBody().getAccessToken();
    }

    public UserInfoResponse getGithubUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(headers);

        return new RestTemplate().exchange(
                userUrl,
                HttpMethod.GET,
                request,
                UserInfoResponse.class
        ).getBody();
    }
}
