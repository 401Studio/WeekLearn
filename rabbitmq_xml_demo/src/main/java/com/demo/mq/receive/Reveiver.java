package com.demo.mq.receive;

import org.springframework.stereotype.Service;
import java.util.concurrent.CountDownLatch;

@Service
public class Reveiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("reveice msg=" + message.toString());
        latch.countDown();
    }
}
