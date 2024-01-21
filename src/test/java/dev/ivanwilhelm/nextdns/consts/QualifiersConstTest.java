package dev.ivanwilhelm.nextdns.consts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.ivanwilhelm.nextdns.consts.QualifiersConst.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QualifiersConstTest {
    @Test
    @DisplayName("When user address job name checked then success")
    void whenUserAddressJobNameChecked_thenSuccess() {
        // Asserts
        assertEquals("updateUserAddressJob", UPDATE_USER_ADDRESS_JOB_NAME);
    }

    @Test
    @DisplayName("When user address step name checked then success")
    void whenUserAddressStepNameChecked_thenSuccess() {
        // Asserts
        assertEquals("updateUserAddressStep", UPDATE_USER_ADDRESS_STEP_NAME);
    }

    @Test
    @DisplayName("When user address tasklet name checked then success")
    void whenUserAddressTaskletNameChecked_thenSuccess() {
        // Asserts
        assertEquals("updateUserAddressTasklet", UPDATE_USER_ADDRESS_TASKLET_NAME);
    }
}