package com.kawakawaryuryu.samplespringbatch.config;

import com.kawakawaryuryu.samplespringbatch.step.tasklet.HelloWorldTasklet;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final HelloWorldTasklet helloWorldTasklet;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       HelloWorldTasklet helloWorldTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.helloWorldTasklet = helloWorldTasklet;
    }

    @Bean
    public Job helloWorldJob(Step helloWorldStep) {
    	log.info("[helloWorldJob]");
        return jobBuilderFactory.get("helloWorldJob")
                .flow(helloWorldStep)
                .end()
                .build();
    }

    @Bean
    public Step helloWorldStep() {
    	log.info("[helloWorldStep]");
        return stepBuilderFactory.get("helloWorldStep")
                .tasklet(helloWorldTasklet)
                .build();
    }
}
