package org.storm.stacktrace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.storm.CommonUtil;

import java.time.Duration;

public class Task {

    public static final Logger logger = LoggerFactory.getLogger(Task.class);

    public static void execute(int taskId) {
        logger.info("Starting Task {}", taskId);
        try {
            method1(taskId);
        } catch (Exception e) {
            logger.error("Task {} failed", taskId, e);
        }
        logger.info("Finished Task {}", taskId);
    }

    private static void method1(int taskId) {
        CommonUtil.sleep(Duration.ofMillis(300));
        try {
            method2(taskId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void method2(int taskId) {
        CommonUtil.sleep(Duration.ofMillis(100));
        method3(taskId);
    }

    private static void method3(int taskId) {
        CommonUtil.sleep(Duration.ofMillis(500));
        if (taskId == 4) {
            throw new IllegalArgumentException("taskId cannot be 4");
        }
    }

}
