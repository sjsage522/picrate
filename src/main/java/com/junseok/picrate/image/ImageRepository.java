package com.junseok.picrate.image;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByHashName(String hashName);
}
