package com.interactions.commands;

import java.util.List;

/**
 * Created by voicestreams on 1/5/15.
 */
public class StateDetector {

    public StateDetector() {
    }

    public State detect(List<Task> subTasks) {
        if(isAnyTaskInState(subTasks, State.ST_FAILED)) {
            return State.ST_FAILED;
        }

        if(isAnyTaskInState(subTasks, State.ST_PROCESSING)) {
            return State.ST_PROCESSING;
        }

        if(isAnyTaskInState(subTasks, State.ST_NEW)) {
            return State.ST_PROCESSING;
        }

        return State.ST_DONE;
    }

    private boolean isAnyTaskInState(List<Task> subTasks, State state) {
        boolean isTrue = false;
        for(Task subTask : subTasks) {
            if(subTask.getState() == state) {
                isTrue = true;
                break;
            }
        }
        return isTrue;
    }
}
