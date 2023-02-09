package com.junseok.picrate.image.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junseok.picrate.image.ImageService;
import com.junseok.picrate.image.dto.ImageResource;

@RequestMapping("api/v1")
@RestController
public class ImageController {
    private final ImageService imageService;

    public ImageController(@Qualifier("imageServiceImpl") ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/image/{hash}")
    public ResponseEntity<byte[]> getImage(@PathVariable String hash) {
        ImageResource imageResource = imageService.getImageResource(hash);

        return ResponseEntity
            .ok()
            .contentType(MediaType.valueOf(imageResource.getMimeType()))
            .body(imageResource.getBytes());
    }
}
