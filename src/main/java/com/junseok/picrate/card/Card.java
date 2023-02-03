package com.junseok.picrate.card;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import com.junseok.picrate.image.Image;
import com.junseok.picrate.model.BaseEntity;

@Getter
@Entity
@Table(name = "card")
public class Card extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private Image image;

    @Builder
    public Card(Image image) {
        this.image = image;
    }

    protected Card() {
        /* empty */
    }
}
