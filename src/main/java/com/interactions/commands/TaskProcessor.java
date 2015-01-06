package com.interactions.commands;

import com.amithmit.service.TaskService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskProcessor {

    private final TaskService taskService;
    private final SymphonyFSM fsm;

    @Autowired
    public TaskProcessor(SymphonyFSM fsm, TaskService service) {
        this.taskService = service;
        this.fsm = fsm;
    }

    @Scheduled(fixedDelay = 1000)
    public void process() {
        List<State> stateList = Lists.newArrayList(State.ST_NEW, State.ST_PROCESSING);
        List<Task> tasks = taskService.getTasks(stateList);

        //System.out.println("Tasks found for processing " + tasks.size());

        for(Task task : tasks) {
            System.out.println("[BEFORE] Handling Task " + task.getType() +
                    " in state " + task.getState());
            Handler handler = fsm.getHandler(task.getState(), task.getType());
            State newState = handler.execute(task);
            System.out.println("[AFTER] State is now " + newState);
            task.setState(newState);
        }
    }
}
