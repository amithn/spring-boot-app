package com.interactions.commands;

/**
 * Created by voicestreams on 1/6/15.
 */
public enum Metric {

    INDUSTRY("INDUSTRY"),
    WEBSITE("WEBSITE");

    private String type;

    Metric(String type) {
        this.type = type;
    }
}
