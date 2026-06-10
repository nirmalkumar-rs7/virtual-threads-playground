package org.storm.cpuintensive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.storm.CommonUtil;

public class Task {

    public static final Logger logger = LoggerFactory.getLogger(Task.class);

    public static void cpuIntensive(int input) {
//        logger.info("Starting CPU intensive task, Thread Info: {}", Thread.currentThread().getName());
        var timeTaken = CommonUtil.timer(() -> FibonacciSequence.findFibonacci(input));
//        logger.info("CPU intensive task finished, Time Taken: {}, Thread Info: {}", timeTaken, Thread.currentThread().getName());
    }
}
