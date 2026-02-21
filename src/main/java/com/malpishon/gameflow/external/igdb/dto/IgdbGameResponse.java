package com.malpishon.gameflow.external.igdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class IgdbGameResponse {

    private Long id;
    private String name;
    private String releaseDate;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getReleaseDate() { return releaseDate; }

    @JsonProperty("first_release_date")
    public void setFirstReleaseDate(Long timestamp) {
        if (timestamp != null) {
            this.releaseDate = LocalDate.ofEpochDay(timestamp / 86400).toString();
        }
    }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
