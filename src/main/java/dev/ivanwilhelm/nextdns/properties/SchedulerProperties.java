package dev.ivanwilhelm.nextdns.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static dev.ivanwilhelm.nextdns.consts.PrefixConst.SCHEDULER_PROPERTY_NAME;

@ConfigurationProperties(prefix = SCHEDULER_PROPERTY_NAME)
public record SchedulerProperties(
        int interval
) {
}
