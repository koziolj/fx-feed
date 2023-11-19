package com.sbna.fxfeed.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class MarketPrice (
    @Id
    val id: Long,
    @Column(nullable = false)
    val name : String,
    @Column(nullable = false)
    val orgBidPrice : Double,
    @Column(nullable = false)
    val orgAskPrice : Double,
    @Column(nullable = false)
    val bidPrice : Double,
    @Column(nullable = false)
    val askPrice : Double,
    @Column(nullable = false)
    val time : LocalDateTime
)