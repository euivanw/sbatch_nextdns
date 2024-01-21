package dev.ivanwilhelm.nextdns.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.util.UriComponentsBuilder;

import static dev.ivanwilhelm.nextdns.consts.PrefixConst.NEXTDNS_PROPERTY_NAME;

@ConfigurationProperties(prefix = NEXTDNS_PROPERTY_NAME)
public record NextDnsProperties(
        String baseUrl,
        String configurationId,
        String accountId
) {
    public String getFullUri() {
        return UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .pathSegment(configurationId, accountId)
                .toUriString();
    }
}
