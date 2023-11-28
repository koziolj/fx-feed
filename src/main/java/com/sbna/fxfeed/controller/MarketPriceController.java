package com.sbna.fxfeed.controller;

import com.sbna.fxfeed.repository.MarketPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class MarketPriceController {

    private final MarketPriceRepository marketPriceRepository;

    @GetMapping("/latest")
    @ResponseBody
    public String latestPrice(@RequestParam String name) {
        return ofNullable(marketPriceRepository.findLatestPrice(name.trim()))
                .map(it ->
                        String.format("""
                                ask: %s
                                bid: %s""", it.getAskPrice(), it.getBidPrice())).orElse("No result");

    }

}