package dev.ivanwilhelm.nextdns.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.UPDATE_USER_ADDRESS_STEP_NAME;
import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.UPDATE_USER_ADDRESS_TASKLET_NAME;

@Configuration
public class UpdateUserAddressStepConfig {
    @Bean(value = UPDATE_USER_ADDRESS_STEP_NAME)
    public Step updateUserAddressStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            @Qualifier(UPDATE_USER_ADDRESS_TASKLET_NAME) Tasklet updateUserAddressTasklet
    ) {
        return new StepBuilder(UPDATE_USER_ADDRESS_STEP_NAME, jobRepository)
                .tasklet(updateUserAddressTasklet, transactionManager)
                .build();
    }
}
