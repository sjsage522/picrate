package com.junseok.picrate.image;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junseok.picrate.image.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByHashName(String hashName);
}
