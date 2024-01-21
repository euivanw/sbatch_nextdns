package dev.ivanwilhelm.nextdns.tasklet;

import dev.ivanwilhelm.nextdns.repository.NextDnsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.ivanwilhelm.nextdns.consts.LogConst.*;
import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.UPDATE_USER_ADDRESS_TASKLET_NAME;

@Configuration
public class UpdateUserAddressTaskletConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserAddressTaskletConfig.class);

    private final NextDnsRepository repository;

    public UpdateUserAddressTaskletConfig(NextDnsRepository repository) {
        this.repository = repository;
    }

    @Bean(value = UPDATE_USER_ADDRESS_TASKLET_NAME)
    public Tasklet updateUserAddressTasklet() {
        return (StepContribution contribution, ChunkContext chunkContext) -> {
            LOGGER.info(LOG_START_TASK);

            try {
                repository.updateUserAddress();
            } catch (Exception exception) {
                final var errorMessage = String.format(LOG_ERROR_TASK, exception.getMessage());
                LOGGER.error(errorMessage, exception);
            }

            LOGGER.info(LOG_FINISH_TASK);

            return RepeatStatus.FINISHED;
        };
    }
}
