package com.rabbitmq.test.com.rabbitmq.test.hello;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wuhuachuan on 16/3/28.
 */
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
