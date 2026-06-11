package org.storm.cooperativescheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.storm.CommonUtil;

import java.time.Duration;

public class CooperativeSchedulingDemo {

    public static final Logger logger = LoggerFactory.getLogger(CooperativeSchedulingDemo.class);

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
    }

    static void main() {
        Thread.Builder builder = Thread.ofVirtual().name("cooperative-scheduling-demo-", 1);
        builder.start(() -> task(1));
        builder.start(() -> task(2));
        builder.start(() -> task(3));
        builder.start(() -> task(4));
        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static void task(int taskId) {
        logger.info("taskId: {} started", taskId);
        for (int i = 0; i < 10; i++) {
            logger.info("thread-{} is printing {}.  Thread: {}", taskId, i, Thread.currentThread());
            if ((taskId == 1 && i % 2 == 0) || (taskId == 2)) {
                Thread.yield();
            }
        }
        logger.info("taskId: {} ended", taskId);
    }

}
