package com.junseok.picrate.image.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageResource {
    private byte[] bytes;

    private String mimeType;

    public ImageResource(byte[] bytes, String mimeType) {
        this.bytes = bytes;
        this.mimeType = mimeType;
    }
}
