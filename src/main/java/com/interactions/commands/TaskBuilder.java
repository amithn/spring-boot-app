package com.interactions.commands;

/**
 * Created by voicestreams on 1/5/15.
 */
public class TaskBuilder {
    private Task Task = new Task();

    public TaskBuilder() {
    }

    public TaskBuilder withId(int id) {
        Task.setId(id);
        return this;
    }

    public TaskBuilder withState(State state) {
        Task.setState(state);
        return this;
    }

    public TaskBuilder withType(Event type) {
        Task.setType(type);
        return this;
    }

    public TaskBuilder withParent(int id) {
        Task.setParentId(id);
        return this;
    }

    public TaskBuilder withRequest(SymphonyRequest request) {
        Task.setRequest(request);
        return this;
    }

    public Task build() {
        return Task;
    }
}
