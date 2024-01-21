package dev.ivanwilhelm.nextdns.scheduler;

import dev.ivanwilhelm.nextdns.exception.SchedulerSetupFailException;
import jakarta.annotation.Nullable;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class UpdateUserAddressJobScheduler extends QuartzJobBean {
    private final Job job;
    private final JobExplorer jobExplorer;
    private final JobLauncher jobLauncher;

    public UpdateUserAddressJobScheduler(Job job, JobExplorer jobExplorer, JobLauncher jobLauncher) {
        this.job = job;
        this.jobExplorer = jobExplorer;
        this.jobLauncher = jobLauncher;
    }

    @Override
    protected void executeInternal(@Nullable JobExecutionContext context) throws SchedulerSetupFailException {
        try {
            final var jobParameters = new JobParametersBuilder(jobExplorer)
                    .getNextJobParameters(job)
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);
        } catch (Exception exception) {
            throw new SchedulerSetupFailException(exception.getMessage(), exception);
        }
    }
}
