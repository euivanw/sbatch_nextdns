package dev.ivanwilhelm.nextdns.consts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.ivanwilhelm.nextdns.consts.PrefixConst.NEXTDNS_PROPERTY_NAME;
import static dev.ivanwilhelm.nextdns.consts.PrefixConst.SCHEDULER_PROPERTY_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrefixConstTest {
    @Test
    @DisplayName("When NextDNS property name checked then success")
    void whenNextDnsPropertyNameChecked_thenSuccess() {
        // Asserts
        assertEquals("nextdns", NEXTDNS_PROPERTY_NAME);
    }

    @Test
    @DisplayName("When scheduler property scheduler checked then success")
    void whenSchedulerPropertySchedulerChecked_thenSuccess() {
        // Asserts
        assertEquals("scheduler", SCHEDULER_PROPERTY_NAME);
    }
}