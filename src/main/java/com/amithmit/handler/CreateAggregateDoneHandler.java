package com.amithmit.handler;

import com.amithmit.service.TaskService;
import com.interactions.commands.Event;
import com.interactions.commands.Handler;
import com.interactions.commands.State;
import com.interactions.commands.Task;
import com.interactions.commands.TaskBuilder;
import com.interactions.commands.TaskIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateAggregateDoneHandler implements Handler {
    private TaskService taskService;

    @Autowired
    public CreateAggregateDoneHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public State execute(Task task) {
        Task newTask = new TaskBuilder().withId(TaskIdGenerator.next())
                .withParent(task.getParentId())
                .withState(State.ST_NEW)
                .withType(Event.EV_FETCH_TOP_X)
                .withResourceId(task.getResourceId())
                .build();
        taskService.addTask(newTask);
        return State.ST_DONE;
    }
}
