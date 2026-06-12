package org.storm.executor.autocloseable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.storm.CommonUtil;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WithoutAutocloseableAndShutDownNow {

    private static final Logger logger = LoggerFactory.getLogger(WithoutAutocloseableAndShutDownNow.class);

    static void main() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(WithoutAutocloseableAndShutDownNow::ioTask);
        logger.info("Task Submitted");
        executorService.shutdownNow(); // If we don't shutdown the executor service, the application will not exit as the thread pool will keep running. This is a common issue when using ExecutorService without proper shutdown.
    }

    /**
     * This method will not be executed, as shutDownNow will not wait for the tasks to complete.
     */
    private static void ioTask() {
        CommonUtil.sleep(Duration.ofSeconds(1));
        logger.info("I/O Task Completed");
    }

}
