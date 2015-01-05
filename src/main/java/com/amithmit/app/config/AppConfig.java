package com.amithmit.app.config;

import com.amithmit.handler.IndustryWebisteReportNewHandler;
import com.amithmit.handler.RankingReportNewHandler;
import com.amithmit.handler.RankingReportProcessingHandler;
import com.interactions.commands.CreateAggregateNewHandler;
import com.interactions.commands.CreateAggregateProcessingHandler;
import com.interactions.commands.DoneStateHandler;
import com.interactions.commands.StateDetector;
import com.interactions.commands.SymphonyFSM;
import com.interactions.commands.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.interactions.commands"})
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
