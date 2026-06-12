package org.storm.executor.autocloseable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.storm.CommonUtil;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WithAutocloseable {

    private static final Logger logger = LoggerFactory.getLogger(WithAutocloseable.class);

    static void main() {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.submit(WithAutocloseable::ioTask);
            logger.info("Task Submitted");
        }
    }

    private static void ioTask() {
        CommonUtil.sleep(Duration.ofSeconds(1));
        logger.info("I/O Task Completed");
    }

}
