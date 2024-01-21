package dev.ivanwilhelm.nextdns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic.Verification;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mockStatic;

class NextDnsApplicationTest {
    @Test
    @DisplayName("When Spring Boot starts, then success")
    void whenSpringBootStarts_thenSuccess() {
        // Arrange
        final var app = mockStatic(SpringApplication.class);
        final var verification = (Verification) SpringApplication.run(NextDnsApplication.class);
        app.when(verification).thenReturn(null);

        // Act & Asserts
        assertDoesNotThrow(() -> NextDnsApplication.main(new String[]{}));
        assertNull(SpringApplication.run(NextDnsApplication.class));
    }
}