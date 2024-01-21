package dev.ivanwilhelm.nextdns.scheduler;

import dev.ivanwilhelm.nextdns.exception.SchedulerSetupFailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.job.JobStep;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateUserAddressJobSchedulerTest {
    private Job job;
    private JobParameters jobParameters;
    private JobLauncher jobLauncher;
    private JobExecutionContext jobExecutionContext;
    private UpdateUserAddressJobScheduler updateUserAddressJobScheduler;

    @BeforeEach
    void setUp() {
        jobLauncher = mock(JobLauncher.class);
        jobExecutionContext = mock(JobExecutionContext.class);

        final var jobRepository = mock(JobRepository.class);
        final var jobStep = mock(JobStep.class);
        final var jobExplorer = mock(JobExplorer.class);

        job = new JobBuilder("mockedJob", jobRepository)
                .start(jobStep)
                .incrementer(new RunIdIncrementer())
                .build();

        jobParameters = new JobParametersBuilder(jobExplorer)
                .getNextJobParameters(job)
                .toJobParameters();

        updateUserAddressJobScheduler = new UpdateUserAddressJobScheduler(job, jobExplorer, jobLauncher);
    }

    @Test
    @DisplayName("When executeInternal called then success")
    void whenExecuteInternalCalled_thenSuccess() throws Exception {
        // Arrange
        when(jobLauncher.run(any(Job.class), any(JobParameters.class))).thenReturn(null);

        // Act
        updateUserAddressJobScheduler.executeInternal(jobExecutionContext);

        // Assert
        verify(jobLauncher, only()).run(job, jobParameters);
        verifyNoMoreInteractions(jobLauncher);
        verifyNoInteractions(jobExecutionContext);
    }

    @Test
    @DisplayName("When executeInternal called then exception")
    void whenExecuteInternalCalled_thenException() throws Exception {
        // Arrange
        doThrow(new RuntimeException()).when(jobLauncher).run(any(Job.class), any(JobParameters.class));

        // Act & Assert
        assertThrows(SchedulerSetupFailException.class, () -> updateUserAddressJobScheduler.executeInternal(jobExecutionContext));

        verify(jobLauncher, only()).run(job, jobParameters);
        verifyNoMoreInteractions(jobLauncher);
        verifyNoInteractions(jobExecutionContext);
    }
}