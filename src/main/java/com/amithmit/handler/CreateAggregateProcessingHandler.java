package com.amithmit.handler;

import com.amithmit.service.BackendAPIClient;
import com.amithmit.service.TaskService;
import com.interactions.commands.Handler;
import com.interactions.commands.State;
import com.interactions.commands.Status;
import com.interactions.commands.StatusResponse;
import com.interactions.commands.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateAggregateProcessingHandler implements Handler {
    private BackendAPIClient backendAPIClient;

    @Autowired
    public CreateAggregateProcessingHandler(BackendAPIClient backendAPIClient) {
        this.backendAPIClient = backendAPIClient;
    }

    @Override
    public State execute(Task task) {
        System.out.println("Checking status of request....");
        StatusResponse response = backendAPIClient.checkStatus(task.getResourceId());
        System.out.println("Status of id: " + task.getResourceId() + " is : " +  response.getStatus());

        //TODO: Handle failure cases
        State state = State.ST_PROCESSING;
        if(Status.DONE == response.getStatus()) {
            state = State.ST_DONE;
        }
        return state;
    }
}
