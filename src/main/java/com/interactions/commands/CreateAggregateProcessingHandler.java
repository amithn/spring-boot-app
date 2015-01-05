package com.interactions.commands;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateAggregateProcessingHandler implements Handler {
    private final TaskService taskService;

    @Autowired
    public CreateAggregateProcessingHandler(TaskService service) {
        this.taskService = service;
    }

    @Override
    public State execute(Task task) {
        System.out.println("Checking status of request....");
        return State.ST_DONE;
    }
}
