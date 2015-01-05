package com.interactions.commands;

/**
 * Created by voicestreams on 1/5/15.
 */
public class Task {
    private int id;
    private Event type;
    private State state;
    private SymphonyRequest request;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getType() {
        return type;
    }

    public void setType(Event type) {
        this.type = type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public SymphonyRequest getRequest() {
        return request;
    }

    public void setRequest(SymphonyRequest request) {
        this.request = request;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}

