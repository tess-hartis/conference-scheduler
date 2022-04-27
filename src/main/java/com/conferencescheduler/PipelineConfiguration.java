package com.conferencescheduler;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class PipelineConfiguration {

    @Bean
    public Pipeline pipeline(ObjectProvider<Command.Handler> handlers){
        return new Pipelinr()
                .with(handlers::stream);
    }
}
