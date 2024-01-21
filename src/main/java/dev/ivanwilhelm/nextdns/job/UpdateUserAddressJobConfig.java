package dev.ivanwilhelm.nextdns.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.UPDATE_USER_ADDRESS_JOB_NAME;
import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.UPDATE_USER_ADDRESS_STEP_NAME;

@Configuration
public class UpdateUserAddressJobConfig {
    @Bean(value = UPDATE_USER_ADDRESS_JOB_NAME)
    public Job updateUserAddressJob(
            JobRepository jobRepository,
            @Qualifier(UPDATE_USER_ADDRESS_STEP_NAME) Step updateUserAddressStep
    ) {
        return new JobBuilder(UPDATE_USER_ADDRESS_JOB_NAME, jobRepository)
                .start(updateUserAddressStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
