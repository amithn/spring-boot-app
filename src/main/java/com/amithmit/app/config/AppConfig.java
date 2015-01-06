package com.amithmit.app.config;

import com.amithmit.handler.IndustryWebisteReportNewHandler;
import com.amithmit.handler.RankingReportNewHandler;
import com.amithmit.handler.RankingReportProcessingHandler;
import com.amithmit.handler.CreateAggregateNewHandler;
import com.amithmit.handler.CreateAggregateProcessingHandler;
import com.interactions.commands.DoneStateHandler;
import com.interactions.commands.StateDetector;
import com.interactions.commands.SymphonyFSM;
import com.amithmit.service.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.interactions.commands", "com.amithmit.*"})
public class AppConfig {

    @Bean
    public TaskService taskService() {
        return new TaskService();
    }

    @Bean
    public SymphonyFSM fsm(TaskService service,
                          RankingReportNewHandler rankingReportNewHandler,
                          RankingReportProcessingHandler rankingReportProcessingHandler,
                          IndustryWebisteReportNewHandler industryWebisteReportNewHandler,
                          CreateAggregateNewHandler createAggregateNewHandler,
                          CreateAggregateProcessingHandler createAggregateProcessingHandler,
                          DoneStateHandler doneStateHandler
                ) {
        return new SymphonyFSM(service, rankingReportNewHandler,
                                        rankingReportProcessingHandler,
                                        industryWebisteReportNewHandler,
                                        createAggregateNewHandler,
                                        createAggregateProcessingHandler,
                                        doneStateHandler);
    }

    @Bean
    public StateDetector stateDetector() {
        return new StateDetector();
    }
}
