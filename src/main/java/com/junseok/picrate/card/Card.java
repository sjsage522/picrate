package com.junseok.picrate.card;

import com.junseok.picrate.image.Image;
import com.junseok.picrate.model.BaseEntity;
import com.junseok.picrate.rating.Rating;

import java.util.*;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "card")
public class Card extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    private List<Rating> ratings = new ArrayList<>();

    @Builder
    public Card(Image image) {
        this.image = image;
    }

    protected Card() {
        /* empty */
    }
}
