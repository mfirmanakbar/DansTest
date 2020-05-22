package com.mfirmanakbar.dans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionResponse {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("company")
    private String company;

    @JsonProperty("company_url")
    private String companyUrl;

    @JsonProperty("location")
    private String location;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("how_to_apply")
    private String howToApply;

    @JsonProperty("company_logo")
    private String companyLogo;

    public PositionResponse() {
    }

    @Builder
    public PositionResponse(String id, String type, String url, String createdAt, String company, String companyUrl, String location, String title, String description, String howToApply, String companyLogo) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.createdAt = createdAt;
        this.company = company;
        this.companyUrl = companyUrl;
        this.location = location;
        this.title = title;
        this.description = description;
        this.howToApply = howToApply;
        this.companyLogo = companyLogo;
    }
}
