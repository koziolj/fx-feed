package com.sbna.fxfeed.consumer.config

interface MessageHandler<T> {

    fun onMessage(message: T)

}