package dev.ivanwilhelm.nextdns.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NextDnsFailedToUpdateExceptionTest {
    @Test
    @DisplayName("When exception created with message then success")
    void whenExceptionCreatedWithMessage_thenSuccess() {
        // Arrange
        final var expectedMessage = "Error message";
        final var cause = new Throwable();

        // Act
        final var exception = new NextDnsFailedToUpdateException(expectedMessage, cause);

        // Asserts
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("When exception created with cause then success")
    void whenExceptionCreatedWithCause_thenSuccess() {
        // Arrange
        final var expectedMessage = "Error message";
        final var cause = new Throwable();

        // Act
        final var exception = new NextDnsFailedToUpdateException(expectedMessage, cause);

        // Asserts
        assertEquals(cause, exception.getCause());
    }
}