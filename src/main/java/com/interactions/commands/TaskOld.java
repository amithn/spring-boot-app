package com.interactions.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskOld {
    private State state;
    List<TaskOld> tasks = new ArrayList<>();

    abstract void execute();

    public void addTask(TaskOld task) {
        tasks.add(task);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public TaskOld next() {
        TaskOld returnedTask = null;
        for (TaskOld task : tasks) {
            if (task.getState() != State.ST_DONE) {
                returnedTask = task;
            }
        }
        return returnedTask;
    }
}
