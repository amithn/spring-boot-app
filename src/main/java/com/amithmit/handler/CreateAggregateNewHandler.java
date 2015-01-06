package com.amithmit.handler;

import com.amithmit.service.BackendAPIClient;
import com.amithmit.service.TaskService;
import com.interactions.commands.Handler;
import com.interactions.commands.State;
import com.interactions.commands.Status;
import com.interactions.commands.StatusResponse;
import com.interactions.commands.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateAggregateNewHandler implements Handler {
    private final TaskService taskService;
    private BackendAPIClient backendAPIClient;

    @Autowired
    public CreateAggregateNewHandler(TaskService service, BackendAPIClient backendAPIClient) {
        this.taskService = service;
        this.backendAPIClient = backendAPIClient;
    }

    @Override
    public State execute(Task task) {
        System.out.println("Making an HTTP request to POST /report ");
        StatusResponse response = backendAPIClient.createAggregate(task.getRequest());
        System.out.println("Status is  " + response.getStatus());

        State state = State.ST_PROCESSING;
        if(response.getStatus() == Status.DONE) {
            state = State.ST_DONE;
        }
        task.setResourceId(response.getId());
        return state;
    }
}
