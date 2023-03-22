package com.junseok.picrate.rating.entity;

import com.junseok.picrate.card.entity.Card;
import com.junseok.picrate.model.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
@Table(name = "rating")
public class Rating extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id")
    private Card card;

    private Double x;

    private Double y;

    private String label;

    @Builder
    private Rating(Card card, Double x, Double y, String label) {
        this.card = card;
        this.x = x;
        this.y = y;
        this.label = label;
    }

    protected Rating() {
        /* empty */
    }
}
