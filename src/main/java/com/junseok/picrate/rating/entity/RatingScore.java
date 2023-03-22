package com.junseok.picrate.rating.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.junseok.picrate.card.entity.Card;
import com.junseok.picrate.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = "rating_score")
public class RatingScore extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rating_id")
    private Rating rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "rater")
    private String rater;

    @Builder
    private RatingScore(Rating rating, Card card, Integer rate, String rater) {
        this.rating = rating;
        this.card = card;
        this.rate = rate;
        this.rater = rater;
    }

    protected RatingScore() {
        /* empty */
    }
}
