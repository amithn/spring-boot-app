package com.amithmit.endpoint;

import com.interactions.commands.Event;
import com.interactions.commands.IndustryWebsiteRequest;
import com.interactions.commands.IndustryWebsiteRequestBuilder;
import com.interactions.commands.RankingReportRequest;
import com.interactions.commands.RankingReportRequestBuilder;
import com.interactions.commands.ReportType;
import com.interactions.commands.State;
import com.interactions.commands.Task;
import com.interactions.commands.TaskBuilder;
import com.interactions.commands.TaskIdGenerator;
import com.interactions.commands.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService eventService) {
        this.taskService = eventService;
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public @ResponseBody EventDto addEvent() {
        IndustryWebsiteRequest industryWebsiteRequest = new IndustryWebsiteRequestBuilder()
                                                  .withExpression("HWVISITS: 'www.linux.com'")
                                                  .withSubject(1)
                                                  .withDateFrom("2014-07-12")
                                                  .withDateTo("2014-07-12")
                                                  .build();

        RankingReportRequest request = new RankingReportRequestBuilder()
                                                .newRankingReportRequest()
                                                .addReportType(ReportType.INDUSTRT_WEBSITE)
                                                .withRequest(industryWebsiteRequest)
                                                .build();


        Task task = new TaskBuilder().withId(TaskIdGenerator.next())
                                        .withParent(0)
                                        .withState(State.ST_NEW)
                                        .withType(Event.EV_RANKING_REPORT)
                                        .withRequest(request)
                                        .build();
        taskService.addTask(task);
        return new EventDto(task.getId());

    }

    public static class EventDto {
        private int id;

        public EventDto(int id) {
            this.id = id;
        }
    }

}