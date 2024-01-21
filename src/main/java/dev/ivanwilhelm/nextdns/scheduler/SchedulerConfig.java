package dev.ivanwilhelm.nextdns.scheduler;

import dev.ivanwilhelm.nextdns.properties.SchedulerProperties;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.ivanwilhelm.nextdns.consts.LogConst.LOG_JOB_INTERVAL;
import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.*;

@Configuration
public class SchedulerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerConfig.class);

    private final SchedulerProperties properties;

    public SchedulerConfig(SchedulerProperties properties) {
        this.properties = properties;
    }

    @Bean(value = QUARTZ_JOB_DETAIL_NAME)
    public JobDetail quartzJobDetail() {
        return JobBuilder
                .newJob(UpdateUserAddressJobScheduler.class)
                .storeDurably()
                .build();
    }

    @Bean(value = SIMPLE_SCHEDULE_NAME)
    public SimpleScheduleBuilder simpleSchedule() {
        final var jobInterval = properties.interval();

        final var logMessage = String.format(LOG_JOB_INTERVAL, jobInterval);
        LOGGER.info(logMessage);

        return SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(jobInterval)
                .repeatForever();
    }

    @Bean(value = JOB_TRIGGER_NAME)
    public Trigger jobTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(quartzJobDetail())
                .withSchedule(simpleSchedule())
                .build();
    }
}
