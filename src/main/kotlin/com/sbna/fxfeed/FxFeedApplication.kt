package com.sbna.fxfeed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FxFeedApplication

fun main(args: Array<String>) {
    runApplication<FxFeedApplication>(*args)
}
