package org.storm.cpuintensive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.storm.CommonUtil;

import java.util.concurrent.CountDownLatch;

public class CPUTaskDemo {

    private static final Logger logger = LoggerFactory.getLogger(CPUTaskDemo.class);
    private static final int TASK_COUNT = 3 * Runtime.getRuntime().availableProcessors();

    static void main() {
        logger.info("Tasks Count: {}", TASK_COUNT);
        int input = 45;
        for (int i = 0; i < 3; i++) {
            long timeTaken = CommonUtil.timer(() -> runTasks(Thread.ofVirtual(), input));
            logger.info("Total Time Taken With Virtual Threads: {}", timeTaken);
            timeTaken = CommonUtil.timer(() -> runTasks(Thread.ofPlatform(), input));
            logger.info("Total Time Taken With Platform: {}", timeTaken);
        }
    }

    private static void runTasks(Thread.Builder builder, int input) {
        CountDownLatch latch = new CountDownLatch(TASK_COUNT);
        for (int i = 1; i <= TASK_COUNT; i++) {
            builder.start(() -> {
                Task.cpuIntensive(input);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
