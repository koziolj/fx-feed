package com.sbna.fxfeed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MarketPrice {
    @Id
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Double orgBidPrice;

    @Column(nullable = false)
    Double orgAskPrice;

    @Column(nullable = false)
    Double bidPrice;

    @Column(nullable = false)
    Double askPrice;

    @Column(nullable = false)
    LocalDateTime time;
}