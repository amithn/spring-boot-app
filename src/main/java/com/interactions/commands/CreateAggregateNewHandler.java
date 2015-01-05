package com.interactions.commands;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateAggregateNewHandler implements Handler {
    private final TaskService taskService;

    @Autowired
    public CreateAggregateNewHandler(TaskService service) {
        this.taskService = service;
    }

    @Override
    public State execute(Task task) {
        System.out.println("Making an HTTP request to POST /report");
        return State.ST_PROCESSING;
    }
}
