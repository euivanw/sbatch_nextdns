package dev.ivanwilhelm.nextdns.scheduler;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import dev.ivanwilhelm.nextdns.properties.SchedulerProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static ch.qos.logback.classic.Level.INFO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SchedulerConfigTest {
    private SchedulerProperties schedulerProperties;
    private SchedulerConfig schedulerConfig;
    private ListAppender<ILoggingEvent> capturedLogs;
    private Logger logger;

    @BeforeEach
    void beforeEach() {
        capturedLogs = new ListAppender<>();
        capturedLogs.start();

        logger = (Logger) LoggerFactory.getLogger(SchedulerConfig.class);
        logger.addAppender(capturedLogs);

        schedulerProperties = mock(SchedulerProperties.class);
        schedulerConfig = new SchedulerConfig(schedulerProperties);
    }

    @AfterEach
    void afterEach() {
        logger.detachAppender(capturedLogs);
    }

    @Test
    @DisplayName("When quartzJobDetail called then success")
    void whenQuartzJobDetailCalled_thenSuccess() {
        // Act
        final var jobDetail = schedulerConfig.quartzJobDetail();

        // Assert
        assertNotNull(jobDetail);
        assertThat(capturedLogs.list).isEmpty();

        verifyNoInteractions(schedulerProperties);
    }

    @Test
    @DisplayName("When simpleSchedule called then success")
    void whenSimpleScheduleCalled_thenSuccess() {
        // Arrange
        final var logMessage = "[NEXTDNS -> SCHEDULER] The task will run every 10 seconds.";

        when(schedulerProperties.interval()).thenReturn(10);

        // Act
        final var schedule = schedulerConfig.simpleSchedule();

        // Assert
        assertNotNull(schedule);
        assertThat(capturedLogs.list).hasSize(1);
        assertThat(capturedLogs.list.get(0).getLevel()).isEqualTo(INFO);
        assertThat(capturedLogs.list.get(0).getMessage()).isEqualTo(logMessage);

        verify(schedulerProperties, only()).interval();
        verifyNoMoreInteractions(schedulerProperties);
    }

    @Test
    @DisplayName("When jobTrigger called then success")
    void whenJobTriggerCalled_thenSuccess() {
        // Arrange
        final var logMessage = "[NEXTDNS -> SCHEDULER] The task will run every 10 seconds.";

        when(schedulerProperties.interval()).thenReturn(10);

        // Act
        final var trigger = schedulerConfig.jobTrigger();

        // Assert
        assertNotNull(trigger);
        assertThat(capturedLogs.list).hasSize(1);
        assertThat(capturedLogs.list.get(0).getLevel()).isEqualTo(INFO);
        assertThat(capturedLogs.list.get(0).getMessage()).isEqualTo(logMessage);

        verify(schedulerProperties, only()).interval();
        verifyNoMoreInteractions(schedulerProperties);
    }
}