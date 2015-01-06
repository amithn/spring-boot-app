package com.interactions.commands;

/**
 * Created by voicestreams on 1/6/15.
 */
public enum Status {

    QUEUED("QUEUED"),
    PROCESSING("PROCESSING"),
    DONE("DONE"),
    FAILED("FAILED");

    private final String status;

    Status(String status) {
        this.status = status;

    }

}
