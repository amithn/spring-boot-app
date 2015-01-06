package com.interactions.commands;

/**
 * Created by voicestreams on 1/5/15.
 */
public class TaskBuilder {
    private Task task = new Task();

    public TaskBuilder() {
    }

    public TaskBuilder withId(int id) {
        task.setId(id);
        return this;
    }

    public TaskBuilder withState(State state) {
        task.setState(state);
        return this;
    }

    public TaskBuilder withType(Event type) {
        task.setType(type);
        return this;
    }

    public TaskBuilder withParent(int id) {
        task.setParentId(id);
        return this;
    }

    public TaskBuilder withRequest(APIRequest request) {
        task.setRequest(request);
        return this;
    }

    public TaskBuilder withResourceId(String id) {
        task.setResourceId(id);
        return this;
    }


    public Task build() {
        return task;
    }
}
