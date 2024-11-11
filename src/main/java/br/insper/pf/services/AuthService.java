package br.insper.pf.services;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.insper.pf.models.UserInfo;


public class AuthService {

    private final RestTemplate restTemplate = new RestTemplate();

    public UserInfo validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<UserInfo> response = restTemplate.exchange(
            "http://184.72.80.215/usuario/validate",
            HttpMethod.GET, entity, UserInfo.class
        );

        return response.getBody();
    }
}

