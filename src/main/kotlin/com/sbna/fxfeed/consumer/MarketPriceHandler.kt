package com.sbna.fxfeed.consumer

import com.sbna.fxfeed.consumer.config.MessageHandler
import com.sbna.fxfeed.consumer.converter.MarketPriceConverter
import com.sbna.fxfeed.repository.MarketPriceRepository
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service


@Service
class MarketPriceHandler(
    private val marketPriceConverter: MarketPriceConverter,
    private val marketPriceRepository: MarketPriceRepository
) : MessageHandler<String> {

    private val logger = KotlinLogging.logger {}

    override fun onMessage(message: String) {
        try {
            marketPriceConverter.convert(message).map { marketPriceRepository.save(it) }
        } catch (e: Exception) {
            logger.error { "Message cannot be handled: ${e.message}" }
        }
    }

    @EventListener(ApplicationReadyEvent::class)
    fun doSomethingAfterStartup() {
        onMessage(
            "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001\n" +
                    "107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002\n" +
                    "108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002\n" +
                    "109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100\n" +
                    "110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110"
        )
    }
}