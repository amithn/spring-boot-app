package com.amithmit.app.config;

import com.amithmit.handler.IndustryWebisteReportNewHandler;
import com.amithmit.handler.RankingReportNewHandler;
import com.amithmit.handler.RankingReportProcessingHandler;
import com.amithmit.handler.CreateAggregateNewHandler;
import com.amithmit.handler.CreateAggregateProcessingHandler;
import com.amithmit.service.BackendAPIClient;
import com.interactions.commands.DoneStateHandler;
import com.interactions.commands.StateDetector;
import com.amithmit.service.TaskService;
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
    public CreateAggregateNewHandler createAggregateNewHandler(BackendAPIClient client, TaskService service) {
        return new CreateAggregateNewHandler(service, client);
    }

    @Bean
    public CreateAggregateProcessingHandler createAggregateProcessingHandler(BackendAPIClient client) {
        return new CreateAggregateProcessingHandler(client);
    }

    @Bean
    public DoneStateHandler doneStateHandler() {
        return new DoneStateHandler();
    }
}
