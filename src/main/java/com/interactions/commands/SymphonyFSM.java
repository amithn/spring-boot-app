package com.interactions.commands;


import com.amithmit.handler.CreateAggregateNewHandler;
import com.amithmit.handler.CreateAggregateProcessingHandler;
import com.amithmit.handler.IndustryWebisteReportNewHandler;
import com.amithmit.handler.RankingReportNewHandler;
import com.amithmit.handler.RankingReportProcessingHandler;
import com.amithmit.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by voicestreams on 1/5/15.
 */
@Component
public class SymphonyFSM {
    private final TaskService taskService;
    private final RankingReportNewHandler rankingReportNewHandler;
    private final RankingReportProcessingHandler rankingReportProcessingHandler;
    private final IndustryWebisteReportNewHandler industryWebisteReportNewHandler;
    private final CreateAggregateNewHandler createAggregateNewHandler;
    private final CreateAggregateProcessingHandler createAggregateProcessingHandler;
    private final DoneStateHandler doneStateHandler;

    Map<State, Map<Event, Handler>> eventHandler = new HashMap<>();

    @Autowired
    public SymphonyFSM(TaskService taskService,
                       RankingReportNewHandler rankingReportNewHandler,
                       RankingReportProcessingHandler rankingReportProcessingHandler,
                       IndustryWebisteReportNewHandler industryWebisteReportNewHandler,
                       CreateAggregateNewHandler createAggregateNewHandler,
                       CreateAggregateProcessingHandler createAggregateProcessingHandler,
                       DoneStateHandler doneStateHandler) {
        this.taskService = taskService;
        this.rankingReportNewHandler = rankingReportNewHandler;
        this.rankingReportProcessingHandler = rankingReportProcessingHandler;
        this.industryWebisteReportNewHandler = industryWebisteReportNewHandler;
        this.createAggregateNewHandler = createAggregateNewHandler;
        this.createAggregateProcessingHandler = createAggregateProcessingHandler;
        this.doneStateHandler = doneStateHandler;
    }

    @PostConstruct
    @Autowired
    public void init() {
        Map<Event, Handler> newStateEventHandler = new HashMap<>();
        newStateEventHandler.put(Event.EV_RANKING_REPORT, rankingReportNewHandler);
        newStateEventHandler.put(Event.EV_INDUSTRY_WEBSITE_REPORT, industryWebisteReportNewHandler);
        newStateEventHandler.put(Event.EV_CREATE_AGGREGATE, createAggregateNewHandler);

        Map<Event, Handler> processingStateEventHandler = new HashMap<>();
        processingStateEventHandler.put(Event.EV_RANKING_REPORT, rankingReportProcessingHandler);
        processingStateEventHandler.put(Event.EV_INDUSTRY_WEBSITE_REPORT, rankingReportProcessingHandler);
        processingStateEventHandler.put(Event.EV_CREATE_AGGREGATE, createAggregateProcessingHandler);

        Map<Event, Handler> doneStateEventHandlers = new HashMap<>();
        doneStateEventHandlers.put(Event.EV_RANKING_REPORT, doneStateHandler);
        doneStateEventHandlers.put(Event.EV_INDUSTRY_WEBSITE_REPORT, doneStateHandler);
        doneStateEventHandlers.put(Event.EV_CREATE_AGGREGATE, doneStateHandler);

        eventHandler.put(State.ST_NEW, newStateEventHandler);
        eventHandler.put(State.ST_PROCESSING, processingStateEventHandler);
        eventHandler.put(State.ST_DONE, doneStateEventHandlers);
    }

    public Handler getHandler(State state, Event eventType) {
        Handler handler = null;
        Map<Event, Handler> stateEventHandler = eventHandler.get(state);
        if(stateEventHandler != null ) {
             handler = stateEventHandler.get(eventType);
            if(handler != null) {
            } else {
                System.out.println("Could not find Handler for [State] -> " + state + " [Event] + " + eventType);
                throw new HandlerNotFoundException();
            }
        }
        return handler;
    }
}
