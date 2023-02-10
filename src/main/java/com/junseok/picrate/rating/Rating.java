package com.junseok.picrate.rating;

import com.junseok.picrate.card.Card;
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

    private Integer rate;

    private String label;

    private String rater;

    @Builder
    private Rating(Card card, Double x, Double y, Integer rate, String label, String rater) {
        this.card = card;
        this.x = x;
        this.y = y;
        this.rate = rate;
        this.label = label;
        this.rater = rater;
    }

    protected Rating() {
        /* empty */
    }

    public void updateRate(Integer rate) {
        this.rate = rate;
    }

    public void setRater(String rater) {
        this.rater = rater;
    }
}
