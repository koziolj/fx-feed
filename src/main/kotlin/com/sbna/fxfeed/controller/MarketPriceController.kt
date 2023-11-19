package com.sbna.fxfeed.controller

import com.sbna.fxfeed.repository.MarketPriceRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/prices")
class MarketPriceController(private var marketPriceRepository: MarketPriceRepository) {

    @RequestMapping(
        value = ["/latest"], method = [RequestMethod.GET], produces = ["application/json"]
    )
    @ResponseBody
    fun latestPrice(@RequestParam name: String): String {
        val price = marketPriceRepository.findLatestPrice(name)

        price.also {
            return """
                |ask: ${it?.askPrice}
                |bid: ${it?.bidPrice}
                |""".trimMargin()
        }
    }

}