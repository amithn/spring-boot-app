package com.interactions.commands;

/**
 * Created by voicestreams on 1/5/15.
 */
public interface Handler {
    public State execute(Task event);
}
