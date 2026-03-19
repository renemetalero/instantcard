package com.orchestration.instantcard.utils;

import com.orchestration.instantcard.context.ApiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DurationUtils {
    private final Logger logger = LoggerFactory.getLogger((getClass()));

    private ApiContext apiContext;

    public DurationUtils(ApiContext apiContext) {
        this.apiContext = apiContext;
    }
    public void durationExecutions(long startTime, String sessionId, String message) {
        long endTime = System.currentTimeMillis();
        long durationMilliseconds = (endTime - startTime);
        long seconds = TimeUnit.MICROSECONDS.toSeconds(durationMilliseconds);
        logger.debug("{} Session Id: {} , Message Id:{}, has take {} seconds ",
                message, sessionId, apiContext.getMetadata().getMessageId(), seconds);
    }
}
