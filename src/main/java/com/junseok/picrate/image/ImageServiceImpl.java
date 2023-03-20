package com.junseok.picrate.image;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junseok.picrate.common.exception.*;
import com.junseok.picrate.image.dto.ImageResource;
import com.junseok.picrate.util.FileUploadUtils;

@Service
public class ImageServiceImpl implements ImageService {
    private final FileUploadUtils fileUploadUtils;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(@Qualifier("fileUploadLocalUtils") FileUploadUtils fileUploadUtils, ImageRepository imageRepository) {
        this.fileUploadUtils = fileUploadUtils;
        this.imageRepository = imageRepository;
    }

    @Transactional(readOnly = true)
    public ImageResource getImageResource(Long id) {
        Image findImage = imageRepository.findById(id).orElseThrow(() -> new NotFoundImageException(ErrorCode.NOT_FOUND_IMAGE));

        byte[] imageBytes = fileUploadUtils.getFileBytes(findImage.getHashName());
        String mimeType = fileUploadUtils.getMimeType(imageBytes).orElseThrow(() -> new NotAllowedMimeTypeException(ErrorCode.NOT_ALLOWED_MIMETYPE));
        return new ImageResource(imageBytes, mimeType);
    } 
}
