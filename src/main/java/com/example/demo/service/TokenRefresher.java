package com.example.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Component
public class TokenRefresher {

    private volatile String token;

    private static final String oAuthUrl = "https://api.petfinder.com/v2/oauth2/token";
    private static final String apiKey = "nB42zysZO7a1TJUYyaMhKhFzxAtAygysmdhppLITsYqSEIoxHI";
    private static final String secret = "s7I7Q9t9ouuYFhTxaEccfvkVx8lSp5cNwQmZk7LL";


    @Scheduled(fixedDelay = 58 * 60 * 1000, initialDelay = 0)
    public void refreshToken() {
        token = null;
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(oAuthUrl)
                .encode()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.put("grant_type", Collections.singletonList("client_credentials"));
        body.put("client_id", Collections.singletonList(apiKey));
        body.put("client_secret", Collections.singletonList(secret));

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        JWTToken response = restTemplate.postForObject(urlTemplate, entity, JWTToken.class);
        if(response != null) {
            this.token = response.access_token;
        }
    }

    public String getToken() {
        while (token == null) {
            System.out.println("Waiting till token refreshes");
        }
        return token;
    }

    private static final class JWTToken {
        public String access_token;
    }
}
