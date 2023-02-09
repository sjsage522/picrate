package com.junseok.picrate.image;

import com.junseok.picrate.image.dto.ImageResource;

public interface ImageService {
   ImageResource getImageResource(String hash);
}
