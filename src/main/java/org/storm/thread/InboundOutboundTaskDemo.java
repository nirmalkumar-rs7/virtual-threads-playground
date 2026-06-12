package org.storm.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {

    public static final Logger log = LoggerFactory.getLogger(InboundOutboundTaskDemo.class);

    private static final int MAX_PLATFORM_THREADS = 100000;
    public static final int MAX_VIRTUAL_THREADS = 1_000_000;

    static void main() {
//        platformThreadDemoWithName();
//        virtualThreadDemo();
    }

    private static void platformThreadDemo() {
        for (int i = 1; i <= MAX_PLATFORM_THREADS; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.ioIntensiveTask(j));
            thread.start();
        }
    }

    private static void platformThreadDemoWithBuilder() {
        for (int i = 1; i <= MAX_PLATFORM_THREADS; i++) {
            int j = i;
            Thread thread = Thread.ofPlatform().unstarted(() -> Task.ioIntensiveTask(j));
            thread.start();
        }
    }

    private static void platformThreadDemoWithName() {
        Thread.Builder builder = Thread.ofPlatform().name("nirmal-", 1);
        for (int i = 1; i <= MAX_PLATFORM_THREADS; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> Task.ioIntensiveTask(j));
            thread.start();
        }
    }

    private static void platformDaemonThreadDemo() {
        CountDownLatch latch = new CountDownLatch(MAX_PLATFORM_THREADS);
        Thread.Builder builder = Thread.ofPlatform().daemon().name("daemon-nirmal-", 1);
        for (int i = 1; i <= MAX_PLATFORM_THREADS; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensiveTask(j);
                latch.countDown();
            });
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error("platformThreadDemo4", e);
        }
    }

    private static void virtualThreadDemo() {
        CountDownLatch latch = new CountDownLatch(MAX_VIRTUAL_THREADS);
        Thread.Builder builder = Thread.ofVirtual().name("virtual-nirmal-", 1);
        for (int i = 1; i <= MAX_VIRTUAL_THREADS; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensiveTask(j);
                latch.countDown();
            });
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error("virtualThreadDemo", e);
        }
    }

}
