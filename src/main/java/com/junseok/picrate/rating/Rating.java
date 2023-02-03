package com.junseok.picrate.rating;

import javax.persistence.*;

import com.junseok.picrate.model.BaseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "rating")
public class Rating extends BaseEntity {
    
}
