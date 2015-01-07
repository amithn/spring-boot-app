package com.amithmit.handler;

import com.interactions.commands.Event;
import com.interactions.commands.Handler;
import com.interactions.commands.IndustryWebsiteRequest;
import com.interactions.commands.IndustryWebsiteRequestBuilder;
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
public class IndustryWebisteReportNewHandler implements Handler {

    private final TaskService taskService;

    @Autowired
    public IndustryWebisteReportNewHandler(TaskService service) {
        this.taskService = service;
    }

    @Override
    public State execute(Task task) {
        Task newTask = null;
        // Create Tasks and move from NEW to Processing
        RankingReportRequest request = (RankingReportRequest) task.getRequest();
        for(ReportType reportType : request.getReports()) {

            if(reportType == ReportType.INDUSTRY_WEBSITES) {
                IndustryWebsiteRequest industryWebsiteRequest = new IndustryWebsiteRequestBuilder()
                                                                    .withExpression("DONUTSY AND HWVISITS:'www.netflix.com OR HWVISITS:'www.amazon.com'")
                                                                    .withRegion("US")
                                                                    .withSubject(1)
                                                                    .withDateFrom("2014-05-17")
                                                                    .withDateTo("2014-12-17")
                                                                    .withType(reportType)
                                                                    .build();

                newTask = new TaskBuilder().withId(TaskIdGenerator.next())
                        .withParent(task.getId())
                        .withState(State.ST_NEW)
                        .withRequest(industryWebsiteRequest)
                        .withType(Event.EV_CREATE_AGGREGATE)
                        .build();

            }

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
