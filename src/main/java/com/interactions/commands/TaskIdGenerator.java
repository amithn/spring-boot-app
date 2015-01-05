package com.interactions.commands;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by voicestreams on 1/5/15.
 */
public class TaskIdGenerator {
    private static AtomicInteger id = new AtomicInteger(0);

    public static int next() {
        return id.incrementAndGet();
    }
}
