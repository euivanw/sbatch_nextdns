package dev.ivanwilhelm.nextdns.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchedulerPropertiesTest {
    @Test
    @DisplayName("When getInterval called then success")
    void whenGetIntervalCalled_thenSuccess() {
        // Arrange
        final var interval = 10;
        final var schedulerProperties = new SchedulerProperties(interval);

        // Act
        final var createdInterval = schedulerProperties.interval();

        // Assert
        assertEquals(10, createdInterval);
    }
}