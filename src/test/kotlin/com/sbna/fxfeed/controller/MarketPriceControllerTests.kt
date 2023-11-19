package com.sbna.fxfeed.controller

import com.ninjasquad.springmockk.MockkBean
import com.sbna.fxfeed.model.MarketPrice
import com.sbna.fxfeed.repository.MarketPriceRepository
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest
class MarketPriceControllerTests(
    @Autowired val mockMvc: MockMvc
) {

    @MockkBean
    lateinit var marketPriceRepository: MarketPriceRepository

    @Test
    fun `Fetch latest price`() {
        val marketPrice = MarketPrice(108, "GBP/USD", 1.2500, 1.2560, 1.2499, 1.2560, LocalDateTime.now())
        every { marketPriceRepository.findLatestPrice("GBP/USD") } returns marketPrice
        mockMvc.perform(
            get("/prices/latest")
                .param("name", "GBP/USD")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(
                content().string(
                    """
                |ask: 1.256
                |bid: 1.2499
                |""".trimMargin()
                )
            )
    }

}