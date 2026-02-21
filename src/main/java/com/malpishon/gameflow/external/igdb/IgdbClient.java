package com.malpishon.gameflow.external.igdb;

import com.malpishon.gameflow.external.igdb.dto.IgdbGameResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class IgdbClient {

    private final RestClient restClient;

    private final IgdbTokenService tokenService;

    @Value("${igdb.client.id}")
    private String clientId;

    public IgdbClient(IgdbTokenService tokenService) {
        this.tokenService = tokenService;
        this.restClient = RestClient.create("https://api.igdb.com/v4");
    }

    public List<IgdbGameResponse> searchGames(String title) {
        String token = tokenService.fetchAccessToken();

        String query = "search \"" + title + "\"; fields name, first_release_date; limit 5;";

        return restClient.post()
                .uri("/games")
                .header("Client-Id", clientId)
                .header("Authorization", "Bearer " + token)
                .body(query)
                .retrieve()
                .body(new ParameterizedTypeReference<List<IgdbGameResponse>>() {});

    }

}
