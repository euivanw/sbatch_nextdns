package dev.ivanwilhelm.nextdns.tasklet;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import dev.ivanwilhelm.nextdns.exception.NextDnsFailedToUpdateException;
import dev.ivanwilhelm.nextdns.repository.NextDnsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;

import static ch.qos.logback.classic.Level.ERROR;
import static ch.qos.logback.classic.Level.INFO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.batch.repeat.RepeatStatus.FINISHED;

class UpdateUserAddressTaskletConfigTest {
    private StepContribution stepContribution;
    private ChunkContext chunkContext;
    private NextDnsRepository nextDnsRepository;
    private UpdateUserAddressTaskletConfig updateUserAddressTaskletConfig;
    private ListAppender<ILoggingEvent> capturedLogs;
    private Logger logger;

    @BeforeEach
    public void beforeEach() {
        capturedLogs = new ListAppender<>();
        capturedLogs.start();

        logger = (Logger) LoggerFactory.getLogger(UpdateUserAddressTaskletConfig.class);
        logger.addAppender(capturedLogs);

        stepContribution = mock(StepContribution.class);
        chunkContext = mock(ChunkContext.class);

        nextDnsRepository = mock(NextDnsRepository.class);
        updateUserAddressTaskletConfig = new UpdateUserAddressTaskletConfig(nextDnsRepository);
    }

    @AfterEach
    void afterEach() {
        logger.detachAppender(capturedLogs);
    }

    @Test
    @DisplayName("When tasklet called then success")
    void whenTaskletCalled_thenSuccess() throws Exception {
        // Arrange
        final var logMessage1 = "[NEXTDNS -> TASKLET] Starting the user's address update task.";
        final var logMessage2 = "[NEXTDNS -> TASKLET] Finishing the task of updating the user's address.";

        doNothing().when(nextDnsRepository).updateUserAddress();

        // Act
        final var taskletResult = updateUserAddressTaskletConfig.updateUserAddressTasklet().execute(stepContribution, chunkContext);

        // Asserts
        assertNotNull(taskletResult);
        assertEquals(FINISHED, taskletResult);
        assertThat(capturedLogs.list).hasSize(2);
        assertThat(capturedLogs.list.get(0).getLevel()).isEqualTo(INFO);
        assertThat(capturedLogs.list.get(0).getMessage()).isEqualTo(logMessage1);
        assertThat(capturedLogs.list.get(1).getLevel()).isEqualTo(INFO);
        assertThat(capturedLogs.list.get(1).getMessage()).isEqualTo(logMessage2);
    }

    @Test
    @DisplayName("When tasklet called and repository fail then success")
    void whenTaskletCalled_AndRepositoryFail_thenSuccess() throws Exception {
        // Arrange
        final var logMessage1 = "[NEXTDNS -> TASKLET] Starting the user's address update task.";
        final var logMessage2 = "[NEXTDNS -> TASKLET] An error occured while trying to run the task. Error: null.";
        final var logMessage3 = "[NEXTDNS -> TASKLET] Finishing the task of updating the user's address.";

        doThrow(NextDnsFailedToUpdateException.class).when(nextDnsRepository).updateUserAddress();

        // Act
        final var taskletResult = updateUserAddressTaskletConfig.updateUserAddressTasklet().execute(stepContribution, chunkContext);

        // Asserts
        assertNotNull(taskletResult);
        assertEquals(FINISHED, taskletResult);
        assertThat(capturedLogs.list).hasSize(3);
        assertThat(capturedLogs.list.get(0).getLevel()).isEqualTo(INFO);
        assertThat(capturedLogs.list.get(0).getMessage()).isEqualTo(logMessage1);
        assertThat(capturedLogs.list.get(1).getLevel()).isEqualTo(ERROR);
        assertThat(capturedLogs.list.get(1).getMessage()).isEqualTo(logMessage2);
        assertThat(capturedLogs.list.get(2).getLevel()).isEqualTo(INFO);
        assertThat(capturedLogs.list.get(2).getMessage()).isEqualTo(logMessage3);
    }
}
