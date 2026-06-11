package org.storm.threadpinning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.storm.CommonUtil;

import java.time.Duration;

public class ThreadPinning {

    private static final Logger  logger = LoggerFactory.getLogger(ThreadPinning.class);

    static {
        System.setProperty("jdk.tracePinnedThreads", "full");
    }

    static void main() {
        threadPinningDemo(Thread.ofVirtual());
        CommonUtil.sleep(Duration.ofSeconds(100));
    }

    //Use Java 21 - 23
    private static void threadPinningDemo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                logger.info("Update Started For Thread {}", Thread.currentThread());
                updateSharedDocument();
                logger.info("Update Finished For Thread {}", Thread.currentThread());
            });
        }

        for (int i = 0; i < 3; i++) {
            builder.start(() -> {
                logger.info("Fetch Started For Thread {}", Thread.currentThread());
                fetchUserProfile();
                logger.info("Fetch Finished For Thread {}", Thread.currentThread());
            });
        }

    }

    private static synchronized void updateSharedDocument() {
        CommonUtil.sleep(Duration.ofSeconds(10));
    }

    private static void fetchUserProfile() {
        CommonUtil.sleep(Duration.ofSeconds(11));
    }

}
