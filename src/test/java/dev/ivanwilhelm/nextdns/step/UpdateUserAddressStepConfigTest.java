package dev.ivanwilhelm.nextdns.step;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.transaction.PlatformTransactionManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class UpdateUserAddressStepConfigTest {
    private UpdateUserAddressStepConfig updateUserAddressStepConfig;
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;
    private Tasklet updateUserAddressTasklet;

    @BeforeEach
    void beforeEach() {
        jobRepository = mock(JobRepository.class);
        transactionManager = mock(PlatformTransactionManager.class);
        updateUserAddressTasklet = mock(Tasklet.class);
        updateUserAddressStepConfig = new UpdateUserAddressStepConfig();
    }

    @Test
    @DisplayName("When step created then not null")
    void whenStepCreated_thenNotNull() {
        // Act
        final var step = updateUserAddressStepConfig.updateUserAddressStep(jobRepository, transactionManager, updateUserAddressTasklet);

        // Asserts
        assertNotNull(step);
    }

    @Test
    @DisplayName("When step created then name is correct")
    void whenStepCreated_thenNameIsCorrect() {
        // Act
        final var step = updateUserAddressStepConfig.updateUserAddressStep(jobRepository, transactionManager, updateUserAddressTasklet);

        // Asserts
        assertEquals("updateUserAddressStep", step.getName());
    }
}