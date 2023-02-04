package com.junseok.picrate.image.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.junseok.picrate.image.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageResponse {
    private Long id;

    @JsonProperty(value = "org_name")
    private String orgName;

    @JsonProperty(value = "hash_name")
    private String hashName;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "size")
    private Long size;

    @Builder
    private ImageResponse(Long id, String orgName, String hashName, String type, Long size) {
        this.id = id;
        this.orgName = orgName;
        this.hashName = hashName;
        this.type = type;
        this.size = size;
    }

    public ImageResponse(Image image) {
        this.id = image.getId();
        this.orgName = image.getOrgName();
        this.hashName = image.getHashName();
        this.type = image.getType();
        this.size = image.getSize();
    }
}
