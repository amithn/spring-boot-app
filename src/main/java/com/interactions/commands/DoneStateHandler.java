package com.interactions.commands;

/**
 * Created by voicestreams on 1/6/15.
 */
public class DoneStateHandler implements Handler {

    public DoneStateHandler() {
    }

    @Override
    public State execute(Task event) {
        System.out.println("Task done event handler for Task " + event.getType());
        return State.ST_DONE;
    }
}
