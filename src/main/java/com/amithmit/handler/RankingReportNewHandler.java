package com.amithmit.handler;

import com.interactions.commands.Event;
import com.interactions.commands.Handler;
import com.interactions.commands.RankingReportRequest;
import com.interactions.commands.ReportType;
import com.interactions.commands.State;
import com.interactions.commands.Task;
import com.interactions.commands.TaskBuilder;
import com.interactions.commands.TaskIdGenerator;
import com.amithmit.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankingReportNewHandler implements Handler {

    private final TaskService taskService;

    @Autowired
    public RankingReportNewHandler(TaskService service) {
        this.taskService = service;
    }

    @Override
    public State execute(Task Task) {
        // Create Tasks and move from NEW to Processing
        RankingReportRequest request = (RankingReportRequest) Task.getRequest();
        for(ReportType reportType : request.getReports()) {
            Task newTask = new TaskBuilder().withId(TaskIdGenerator.next())
                    .withParent(Task.getId())
                    .withState(State.ST_NEW)
                    .withRequest(Task.getRequest())
                    .withType(getTaskType(reportType))
                    .build();
            taskService.addTask(newTask);
        }
        return State.ST_PROCESSING;
    }

    private Event getTaskType(ReportType reportType) {
        if(reportType == ReportType.INDUSTRY_WEBSITES) {
            return Event.EV_INDUSTRY_WEBSITE_REPORT;
        } else {
            return Event.EV_SEARCH_TERMS_REPORT;
        }
    }

}
