package com.junseok.picrate.image;

import javax.persistence.*;

import com.junseok.picrate.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;

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

    @Builder
    private Image(String orgName, String hashName, String type) {
        this.orgName = orgName;
        this.hashName = hashName;
        this.type = type;
    }

    protected Image() {
        /* empty */
    }
}
