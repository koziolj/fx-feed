package com.sbna.fxfeed.consumer;

import com.sbna.fxfeed.consumer.config.MessageHandler;
import com.sbna.fxfeed.consumer.converter.MarketPriceConverter;
import com.sbna.fxfeed.repository.MarketPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;


@Service
@RequiredArgsConstructor
@Slf4j
class MarketPriceHandler implements MessageHandler<String> {

    private final MarketPriceConverter marketPriceConverter;
    private final MarketPriceRepository marketPriceRepository;

    public void onMessage(String message) {
        try {
            marketPriceRepository.saveAll(marketPriceConverter.convert(message));
        } catch (Exception e) {
            log.error(format("Message cannot be handled: %s", e.getMessage()));
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        onMessage(
                """
                        106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
                        107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002
                        108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002
                        109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100
                        110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110"""
        );
    }
}