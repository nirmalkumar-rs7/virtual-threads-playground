package org.storm.stacktrace;

import org.storm.CommonUtil;

import java.time.Duration;

public class StackTraceDemo {

    static void main() {
        demo(Thread.ofVirtual());
        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 1; i <= 20; i++) {
            int j = i;
            builder.start(() -> Task.execute(j));
        }
    }

}
