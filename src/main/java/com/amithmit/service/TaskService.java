package com.amithmit.service;

import com.interactions.commands.State;
import com.interactions.commands.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskService {
    private List<Task> TaskList = new ArrayList<>();

    public void addTask(Task Task) {
        TaskList.add(Task);
    }

    public List<Task> getSubTasks(int id) {
        List<Task> childrenList = new ArrayList<>();
        for(Task Task: TaskList) {
            if(Task.getParentId() == id) {
                childrenList.add(Task);
            }
        }
        return childrenList;
    }

    public List<Task> getTasks(List<State> states) {
        List<Task> filteredTasks = new ArrayList<>();
        for(Task Task : TaskList) {
            for(State state : states) {
                if(Task.getState().equals(state)) {
                    filteredTasks.add(Task);
                }
            }
        }
        return filteredTasks;
    }

    public List<Task> getTasks(State state) {
        List<Task> filteredTasks = new ArrayList<>();
        for(Task Task : TaskList) {
                if(Task.getState().equals(state)) {
                    filteredTasks.add(Task);
                }
        }
        return filteredTasks;
    }
}
