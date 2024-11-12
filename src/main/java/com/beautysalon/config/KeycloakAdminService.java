package com.beautysalon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class KeycloakAdminService {

    private final RestClient restClient;
    private final String serverUrl;
    private final String realm;
    private final String clientId;
    private final String clientSecret;

    public KeycloakAdminService(RestClient restClient,
                                @Value("${spring.keycloak.server-url}") String serverUrl,
                                @Value("${spring.keycloak.realm}") String realm,
                                @Value("${spring.keycloak.client-id}") String clientId,
                                @Value("${spring.keycloak.client-secret}") String clientSecret) {
        this.restClient= restClient;
        this.serverUrl = serverUrl;
        this.realm = realm;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
    public String getAccessToken() {
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", serverUrl, realm);

        Map<String, String> response = restClient.post()
                .uri(tokenUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body("client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=client_credentials")
                .retrieve()
                .body(Map.class);

        return response.get("access_token");
    }

    public String getUsers() {
        String token = getAccessToken();
        String userUrl = String.format("%s/admin/realms/%s/users", serverUrl, realm);
        return restClient
                .get()
                .uri(userUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ token)
                .retrieve()
                .body(String.class);
    }

}
