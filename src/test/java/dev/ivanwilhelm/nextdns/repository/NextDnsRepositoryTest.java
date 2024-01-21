package dev.ivanwilhelm.nextdns.repository;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import dev.ivanwilhelm.nextdns.exception.NextDnsFailedToUpdateException;
import dev.ivanwilhelm.nextdns.properties.NextDnsProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import static ch.qos.logback.classic.Level.INFO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class NextDnsRepositoryTest {
    private NextDnsProperties nextDnsProperties;
    private RestTemplate restTemplate;
    private NextDnsRepository nextDnsRepository;
    private ListAppender<ILoggingEvent> capturedLogs;
    private Logger logger;

    @BeforeEach
    void beforeEach() {
        capturedLogs = new ListAppender<>();
        capturedLogs.start();

        logger = (Logger) LoggerFactory.getLogger(NextDnsRepository.class);
        logger.addAppender(capturedLogs);

        nextDnsProperties = mock(NextDnsProperties.class);
        restTemplate = mock(RestTemplate.class);
        nextDnsRepository = new NextDnsRepository(nextDnsProperties, restTemplate);
    }

    @AfterEach
    void afterEach() {
        logger.detachAppender(capturedLogs);
    }

    @Test
    @DisplayName("When updateUserAddress called then success")
    void whenUpdateUserAddressCalled_thenSuccess() throws NextDnsFailedToUpdateException {
        // Arrange
        final var uri = "https://ivanwilhelm.dev";
        final var restResponse = "192.168.0.1";
        final var logMessage = "[NEXTDNS -> REPOSITORY] Update occurred successfully. API response: 192.168.0.1.";

        when(nextDnsProperties.getFullUri()).thenReturn(uri);
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(restResponse);

        // Act
        nextDnsRepository.updateUserAddress();

        // Assert
        assertThat(capturedLogs.list).hasSize(1);
        assertThat(capturedLogs.list.get(0).getLevel()).isEqualTo(INFO);
        assertThat(capturedLogs.list.get(0).getMessage()).isEqualTo(logMessage);

        verify(nextDnsProperties, times(1)).getFullUri();
        verify(restTemplate, times(1)).getForObject(uri, String.class);
    }

    @Test
    @DisplayName("When updateUserAddress called then exception")
    void whenUpdateUserAddressCalled_thenException() {
        // Arrange
        final var uri = "https://ivanwilhelm.dev";

        when(nextDnsProperties.getFullUri()).thenReturn(uri);
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(NextDnsFailedToUpdateException.class, () -> nextDnsRepository.updateUserAddress());
        assertThat(capturedLogs.list).isEmpty();

        verify(nextDnsProperties, times(1)).getFullUri();
        verify(restTemplate, times(1)).getForObject(uri, String.class);
    }
}