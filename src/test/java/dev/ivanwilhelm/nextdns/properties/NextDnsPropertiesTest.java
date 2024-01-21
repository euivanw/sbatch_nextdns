package dev.ivanwilhelm.nextdns.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NextDnsPropertiesTest {
    @Test
    @DisplayName("When getBaseUrl called then success")
    void whenGetBaseUrlCalled_thenSuccess() {
        // Arrange
        final var baseUrl = "https://ivanwilhelm.dev";
        final var configurationId = "23798da23123";
        final var accountId = "98hd8as";
        final var nextDnsProperties = new NextDnsProperties(baseUrl, configurationId, accountId);

        // Act
        final var createdBaseUrl = nextDnsProperties.baseUrl();

        // Assert
        assertEquals("https://ivanwilhelm.dev", createdBaseUrl);
    }

    @Test
    @DisplayName("When getConfigurationId called then success")
    void whenGetConfigurationIdCalled_thenSuccess() {
        // Arrange
        final var baseUrl = "https://ivanwilhelm.dev";
        final var configurationId = "23798da23123";
        final var accountId = "98hd8as";
        final var nextDnsProperties = new NextDnsProperties(baseUrl, configurationId, accountId);

        // Act
        final var createdConfigurationId = nextDnsProperties.configurationId();

        // Assert
        assertEquals("23798da23123", createdConfigurationId);
    }

    @Test
    @DisplayName("When getAccountId called then success")
    void whenGetAccountIdCalled_thenSuccess() {
        // Arrange
        final var baseUrl = "https://ivanwilhelm.dev";
        final var configurationId = "23798da23123";
        final var accountId = "98hd8as";
        final var nextDnsProperties = new NextDnsProperties(baseUrl, configurationId, accountId);

        // Act
        final var createdAccountId = nextDnsProperties.accountId();

        // Assert
        assertEquals("98hd8as", createdAccountId);
    }

    @Test
    @DisplayName("When getFullUri called then success")
    void whenGetFullUriCalled_thenSuccess() {
        // Arrange
        final var baseUrl = "https://ivanwilhelm.dev";
        final var configurationId = "23798da23123";
        final var accountId = "98hd8as";
        final var nextDnsProperties = new NextDnsProperties(baseUrl, configurationId, accountId);

        // Act
        final var fullUri = nextDnsProperties.getFullUri();

        // Assert
        assertEquals("https://ivanwilhelm.dev/23798da23123/98hd8as", fullUri);
    }
}