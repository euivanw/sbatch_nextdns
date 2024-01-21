package dev.ivanwilhelm.nextdns.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UpdateUserAddressJobConfigTest {
    private UpdateUserAddressJobConfig updateUserAddressJobConfig;
    private JobRepository jobRepository;
    private Step updateUserAddressStep;

    @BeforeEach
    void beforeEach() {
        jobRepository = mock(JobRepository.class);
        updateUserAddressStep = mock(Step.class);
        updateUserAddressJobConfig = new UpdateUserAddressJobConfig();
    }

    @Test
    @DisplayName("When job created then not null")
    void whenJobCreated_thenNotNull() {
        // Act
        final var job = updateUserAddressJobConfig.updateUserAddressJob(jobRepository, updateUserAddressStep);

        // Asserts
        assertNotNull(job);
    }

    @Test
    @DisplayName("When job created then name is correct")
    void whenJobCreated_thenNameIsCorrect() {
        // Act
        final var job = updateUserAddressJobConfig.updateUserAddressJob(jobRepository, updateUserAddressStep);

        // Asserts
        assertEquals("updateUserAddressJob", job.getName());
    }

    @Test
    @DisplayName("When job created then parameters incrementer is RunIdIncrementer")
    void whenJobCreated_thenParametersIncrementerIsRunIdIncrementer() {
        // Act
        final var job = updateUserAddressJobConfig.updateUserAddressJob(jobRepository, updateUserAddressStep);

        // Asserts
        assertInstanceOf(RunIdIncrementer.class, job.getJobParametersIncrementer());
    }

    @Test
    @DisplayName("When job created then parameters validator is JobParametersValidator")
    void whenJobCreated_thenParametersValidatorIsJobParametersValidator() {
        // Act
        final var job = updateUserAddressJobConfig.updateUserAddressJob(jobRepository, updateUserAddressStep);

        // Asserts
        assertInstanceOf(JobParametersValidator.class, job.getJobParametersValidator());
    }

    @Test
    @DisplayName("When job created then job is restartable")
    void whenJobCreated_thenJobIsRestartable() {
        // Act
        final var job = updateUserAddressJobConfig.updateUserAddressJob(jobRepository, updateUserAddressStep);

        // Asserts
        assertTrue(job.isRestartable());
    }
}