package org.confiz.template;

import java.time.Duration;
import java.time.LocalTime;

public abstract class AbstractTimeLoggingTask implements TimeLoggingTask{

    public void runWithTimeLogging() {
        LocalTime start = LocalTime.now();
        run();
        LocalTime end = LocalTime.now();
        System.out.println("Execution took: " + Duration.between(start, end).toMillis());
    }

}
