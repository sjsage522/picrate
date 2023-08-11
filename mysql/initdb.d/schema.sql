CREATE TABLE IF NOT EXISTS `image`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime(6)                             DEFAULT NULL,
    `modified_at` datetime(6)                             DEFAULT NULL,
    `hash_name`   varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `org_name`    varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `size`        bigint                                  DEFAULT NULL,
    `type`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `card`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime(6) DEFAULT NULL,
    `modified_at` datetime(6) DEFAULT NULL,
    `image_id`    bigint NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_image_id` (`image_id`),
    CONSTRAINT `FK_image_id_card_id` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `rating`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime(6)                             DEFAULT NULL,
    `modified_at` datetime(6)                             DEFAULT NULL,
    `label`       varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `x`           double                                  DEFAULT NULL,
    `y`           double                                  DEFAULT NULL,
    `card_id`     bigint NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_card_id_rating_id` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `rating_score`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime(6)                             DEFAULT NULL,
    `modified_at` datetime(6)                             DEFAULT NULL,
    `rate`        int                                     DEFAULT NULL,
    `rater`       varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `card_id`     bigint NOT NULL,
    `rating_id`   bigint NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_rating_score_id_rating_id` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`),
    CONSTRAINT `FK_rating_score_id_card_id` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;