package com.malpishon.gameflow.external.igdb;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class IgdbTokenService {

    private final RestClient restClient;

    @Value("${igdb.client.id}")
    private String clientId;

    @Value("${igdb.client.secret}")
    private String clientSecret;

    @Value("${igdb.auth-url}")
    private String authUrl;

    private String accessToken;

    public IgdbTokenService() {
        this.restClient = RestClient.create();
    }

    @PostConstruct
    public void testConnection() {
        System.out.println("🚀 Próba połączenia z IGDB...");
        try {
            String token = fetchAccessToken();
            System.out.println("✅ IGDB Token pobrany: " + token.substring(0, 10) + "...");
        } catch (Exception e) {
            System.err.println("❌ BŁĄD IGDB: " + e.getMessage());
        }
    }

    public String fetchAccessToken() {
        if (accessToken != null) return accessToken;

        Map<String, Object> response = restClient.post()
                .uri(authUrl + "?client_id={id}&client_secret={secret}&grant_type=client_credentials",
                        clientId, clientSecret)
                .retrieve()
                .body(Map.class);

        if (response != null && response.containsKey("access_token")) {
            this.accessToken = (String) response.get("access_token");
            System.out.println("IGDB Token successfully downloaded");
            return accessToken;
        }

        throw new RuntimeException("Couldn't download IGDB token");
    }
}
