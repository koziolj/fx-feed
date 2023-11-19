package com.sbna.fxfeed.consumer.converter

import com.sbna.fxfeed.model.MarketPrice
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Component
class MarketPriceConverter {

    @Value("\${converter.commission.ask}")
    private val ask: Double? = null

    @Value("\${converter.commission.bid}")
    private val bid: Double? = null

    @Value("\${converter.delimiter}")
    private val delimiter: String? = null

    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy' 'HH:mm:ss:SSS", Locale.ENGLISH)

    fun convert(message: String): List<MarketPrice> {
        val transformLine: (String) -> List<String> = { it.split(delimiter!!) }
        val transformField: (List<String>) -> MarketPrice = {
            MarketPrice(
                it[0].toLong(),
                it[1],
                it[2].toDouble(),
                it[3].toDouble(),
                applyCommission(it[2].toDouble(), bid),
                applyCommission(it[3].toDouble(), ask),
                LocalDateTime.parse(it[4], formatter)
            )
        }
        return message.lines().map(
            transformLine
        ).map(transformField)
    }

    fun applyCommission(price: Double, commission: Double?): Double {
        return price + price * commission!!
    }
}