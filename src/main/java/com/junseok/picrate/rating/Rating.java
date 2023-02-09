package com.junseok.picrate.rating;

import com.junseok.picrate.card.Card;
import com.junseok.picrate.model.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
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

    private Integer rate;

    private String label;

    private String name;

    @Builder
    private Rating(Card card, Double x, Double y, Integer rate, String label, String name) {
        this.card = card;
        this.x = x;
        this.y = y;
        this.rate = rate;
        this.label = label;
        this.name = name;
    }

    protected Rating() {
        /* empty */
    }
}
