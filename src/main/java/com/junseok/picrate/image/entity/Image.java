package com.junseok.picrate.image.entity;

import com.junseok.picrate.model.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "image")
public class Image extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "hash_name")
    private String hashName;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private Long size;

    @Builder
    private Image(String orgName, String hashName, String type, Long size) {
        this.orgName = orgName;
        this.hashName = hashName;
        this.type = type;
        this.size = size;
    }

    protected Image() {
        /* empty */
    }
}
