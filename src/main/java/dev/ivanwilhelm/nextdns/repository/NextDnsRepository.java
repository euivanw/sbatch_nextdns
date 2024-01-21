package dev.ivanwilhelm.nextdns.repository;

import dev.ivanwilhelm.nextdns.exception.NextDnsFailedToUpdateException;
import dev.ivanwilhelm.nextdns.properties.NextDnsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static dev.ivanwilhelm.nextdns.consts.LogConst.LOG_UPDATE;
import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.REST_TEMPLATE_NAME;

@Repository
public class NextDnsRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(NextDnsRepository.class);

    private final NextDnsProperties properties;
    private final RestTemplate restTemplate;

    public NextDnsRepository(
            NextDnsProperties properties,
            @Qualifier(REST_TEMPLATE_NAME) RestTemplate restTemplate
    ) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    public void updateUserAddress() throws NextDnsFailedToUpdateException {
        try {
            final var nextDnsUri = properties.getFullUri();
            final var nextDnsResponse = restTemplate.getForObject(nextDnsUri, String.class);

            final var updateLog = String.format(LOG_UPDATE, nextDnsResponse);
            LOGGER.info(updateLog);
        } catch (Exception exception) {
            throw new NextDnsFailedToUpdateException(exception.getMessage(), exception);
        }
    }
}
