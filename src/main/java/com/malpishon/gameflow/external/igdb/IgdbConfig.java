package com.malpishon.gameflow.external.igdb;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgdbConfig {

    @Value("${igdb.client.id}")
    private String clientId;

    @Value("${igdb.client.secret}")
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
