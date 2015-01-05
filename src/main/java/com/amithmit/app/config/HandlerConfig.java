package com.amithmit.app.config;

import com.amithmit.handler.IndustryWebisteReportNewHandler;
import com.amithmit.handler.RankingReportNewHandler;
import com.amithmit.handler.RankingReportProcessingHandler;
import com.interactions.commands.CreateAggregateNewHandler;
import com.interactions.commands.CreateAggregateProcessingHandler;
import com.interactions.commands.DoneStateHandler;
import com.interactions.commands.StateDetector;
import com.interactions.commands.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.interactions.commands"})
public class HandlerConfig {

    @Bean
    public RankingReportNewHandler rankingReportNewHandler(TaskService service) {
        return new RankingReportNewHandler(service);
    }

    @Bean
    public RankingReportProcessingHandler rankingReportProcessingHandler(TaskService service,
                                                                  StateDetector stateDetector) {
        return new RankingReportProcessingHandler(service, stateDetector);
    }

    @Bean
    public IndustryWebisteReportNewHandler industryWebisteReportNewHandler(TaskService service,
                                                                         StateDetector stateDetector) {
        return new IndustryWebisteReportNewHandler(service);
    }

    @Bean
    public CreateAggregateNewHandler createAggregateNewHandler(TaskService service) {
        return new CreateAggregateNewHandler(service);
    }

    @Bean
    public CreateAggregateProcessingHandler createAggregateProcessingHandler(TaskService service) {
        return new CreateAggregateProcessingHandler(service);
    }

    @Bean
    public DoneStateHandler doneStateHandler() {
        return new DoneStateHandler();
    }
}
