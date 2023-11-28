package com.sbna.fxfeed.consumer.converter;

import com.sbna.fxfeed.model.MarketPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static java.lang.Long.parseLong;


@Component
public class MarketPriceConverter {

    @Value("${converter.commission.ask}")
    private Double ask;

    @Value("${converter.commission.bid}")
    private Double bid;

    @Value("${converter.delimiter}")
    private String delimiter;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy' 'HH:mm:ss:SSS", Locale.ENGLISH);

    public List<MarketPrice> convert(String message) {
        return message.lines()
                .map(it -> it.split(delimiter))
                .map(it -> new MarketPrice(
                        parseLong(it[0]),
                        it[1].trim(),
                        Double.valueOf(it[2]),
                        Double.valueOf(it[3]),
                        applyCommission(Double.valueOf(it[2]), bid),
                        applyCommission(Double.valueOf(it[3]), ask),
                        LocalDateTime.parse(it[4], formatter)
                )).toList();
    }

    private Double applyCommission(Double price, Double commission) {
        return price + price * commission;
    }
}