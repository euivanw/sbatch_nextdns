package dev.ivanwilhelm.nextdns.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class NextDnsConfigTest {
    private NextDnsConfig nextDnsConfig;

    @BeforeEach
    void beforeEach() {
        nextDnsConfig = new NextDnsConfig();
    }

    @Test
    @DisplayName("When restTemplate called then success")
    void whenRestTemplateCalled_thenSuccess() {
        // Act
        final var restTemplate = nextDnsConfig.restTemplate();

        // Assert
        assertNull(restTemplate);
    }
}