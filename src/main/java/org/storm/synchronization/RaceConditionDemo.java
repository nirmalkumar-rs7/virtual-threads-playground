package org.storm.synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.storm.CommonUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RaceConditionDemo {

    private static final Logger logger = LoggerFactory.getLogger(RaceConditionDemo.class);
    private static List<Long> list = new ArrayList<>();

    static void main() {
//        demo(Thread.ofPlatform());
        demo(Thread.ofVirtual());
        CommonUtil.sleep(Duration.ofSeconds(2));
        logger.info("list size: {}", list.size());
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                logger.info("Task Strated {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    inMemoryTask();
                }
                logger.info("Task Completed {}", Thread.currentThread());
            });
        }
    }

    private static void inMemoryTask() {
        list.add(1L);
    }

}
