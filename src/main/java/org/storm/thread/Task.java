package org.storm.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {

    public static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void ioIntensiveTask(int taskId) {
        try  {
            log.info("Starting I/O Intensive Task {}, Thread Info {}", taskId, Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(10));
            log.info("Ending I/O Intensive Task {}, Thread Info {}", taskId, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
