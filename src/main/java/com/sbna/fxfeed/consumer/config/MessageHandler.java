package com.sbna.fxfeed.consumer.config;

public interface MessageHandler<T> {

    void onMessage(T message);

}