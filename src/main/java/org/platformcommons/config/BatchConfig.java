package org.platformcommons.config;

import org.platformcommons.batch.AssessmentDataProcessor;
import org.platformcommons.batch.PtldDbReader;
import org.platformcommons.batch.AssessmentDbWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.platformcommons.domain.ptld.AssessmentPtld;
import org.platformcommons.domain.assessmentdb.Assessment;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    PtldDbReader ptldDbReader;

    @Autowired
    AssessmentDbWriter assessmentDbWriter;

    @Autowired
    AssessmentDataProcessor assessmentDataProcessor;

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("Assessment-Data-Job")
                .incrementer(new RunIdIncrementer())
                .flow(createStep()).end().build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("Read-Ptld-Process-Write-AssessmentDB")
                .<AssessmentPtld, Assessment> chunk(1)
                .reader(ptldDbReader)
                .processor(assessmentDataProcessor)
                .writer(assessmentDbWriter)
                .build();
    }
}
