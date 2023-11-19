package com.sbna.fxfeed.repository

import com.sbna.fxfeed.model.MarketPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime

@DataJpaTest
class MarketPriceRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val marketPriceRepository: MarketPriceRepository
) {

    @Test
    fun `When findLatestPrice then return MarketPrice`() {
        val price1 = MarketPrice(108, "GBP/USD", 1.2500, 1.2560, 1.2499, 1.2560, LocalDateTime.now())
        val price2 = MarketPrice(109, "GBP/USD", 1.2501, 1.2561, 1.2500, 1.2562, LocalDateTime.now().plusDays(1))
        entityManager.persist(price1)
        entityManager.persist(price2)
        entityManager.flush()
        val found = marketPriceRepository.findLatestPrice("GBP/USD")
        assertThat(found).isEqualTo(price2)
    }

}