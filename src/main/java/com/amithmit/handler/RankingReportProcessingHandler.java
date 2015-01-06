package com.amithmit.handler;

import com.interactions.commands.Handler;
import com.interactions.commands.State;
import com.interactions.commands.StateDetector;
import com.interactions.commands.Task;
import com.amithmit.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by voicestreams on 1/5/15.
 */
@Component
public class RankingReportProcessingHandler implements Handler {

    private final TaskService taskService;
    private final StateDetector stateDetector;

    @Autowired
    public RankingReportProcessingHandler(TaskService service, StateDetector stateDetector) {
        this.taskService = service;
        this.stateDetector = stateDetector;
    }

    @Override
    public State execute(Task task) {
        Boolean allDone = true;
        State state = State.ST_DONE;

        // Check sub task state and move state accordingly;
        // If any of the sub tasks are in FAILED state the state moves to FAILED as well
        // If any of the sub tasks are in PROCESSING state the state remains in PROCESSING
        // If all of the sub tasks are in DONE - transition to DONE


        List<Task> subTasks = taskService.getSubTasks(task.getId());
        return stateDetector.detect(subTasks);
    }

}
