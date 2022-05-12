package com.kawakawaryuryu.samplespringbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class ExampleJobConfigWithFlow {

    @Autowired public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job ExampleJob2(){
    	log.info("ExampleJob2");
        Job exampleJob = jobBuilderFactory.get("exampleJob2")
                .start(startStep2())
                    .on("FAILED") //startStep의 ExitStatus가 FAILED일 경우
                    .to(failOverStep2()) //failOver Step을 실행 시킨다.
                    .on("*") //failOver Step의 결과와 상관없이
                    .to(writeStep2()) //write Step을 실행 시킨다.
                    .on("*") //write Step의 결과와 상관없 이
                    .end() //Flow를 종료시킨다.

                .from(startStep2()) //startStep이 FAILED가 아니고
                    .on("COMPLETED") //COMPLETED일 경우
                    .to(processStep2()) //process Step을 실행 시킨다
                    .on("*") //process Step의 결과와 상관없이
                    .to(writeStep2()) // write Step을 실행 시킨다.
                    .on("*") //wrtie Step의 결과와 상관없이
                    .end() //Flow를 종료 시킨다.

                .from(startStep2()) //startStep의 결과가 FAILED, COMPLETED가 아닌
                    .on("*") //모든 경우
                    .to(writeStep2()) //write Step을 실행시킨다.
                    .on("*") //write Step의 결과와 상관없이
                    .end() //Flow를 종료시킨다.
                .end()
                .build();

        return exampleJob;
    }

    @Bean
    public Step startStep2() {
        return stepBuilderFactory.get("startStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Start Step!");

                    //String result = "COMPLETED";
                    String result = "FAIL";
                    //String result = "UNKNOWN";

                    //Flow에서 on은 RepeatStatus가 아닌 ExitStatus를 바라본다.
                    if(result.equals("COMPLETED")) 
                        contribution.setExitStatus(ExitStatus.COMPLETED);
                    else if(result.equals("FAIL"))  
                        contribution.setExitStatus(ExitStatus.FAILED);
                    else if(result.equals("UNKNOWN"))  
                        contribution.setExitStatus(ExitStatus.UNKNOWN);

                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step failOverStep2(){
        return stepBuilderFactory.get("nextStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("FailOver Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step processStep2(){
        return stepBuilderFactory.get("processStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Process Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


    @Bean
    public Step writeStep2(){
        return stepBuilderFactory.get("writeStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Write Step!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


}