package dev.ivanwilhelm.nextdns.consts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static dev.ivanwilhelm.nextdns.consts.LogConst.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LogConstTest {
    @Test
    @DisplayName("When log job interval checked then success")
    void whenLogJobIntervalChecked_thenSuccess() {
        // Asserts
        assertEquals("[NEXTDNS -> SCHEDULER] The task will run every %s seconds.", LOG_JOB_INTERVAL);
    }

    @Test
    @DisplayName("When log update checked then success")
    void whenLogUpdateChecked_thenSuccess() {
        // Asserts
        assertEquals("[NEXTDNS -> REPOSITORY] Update occurred successfully. API response: %s.", LOG_UPDATE);
    }

    @Test
    @DisplayName("When log start task checked then success")
    void whenLogStartTaskChecked_thenSuccess() {
        // Asserts
        assertEquals("[NEXTDNS -> TASKLET] Starting the user's address update task.", LOG_START_TASK);
    }

    @Test
    @DisplayName("When log finish task checked then success")
    void whenLogFinishTaskChecked_thenSuccess() {
        // Asserts
        assertEquals("[NEXTDNS -> TASKLET] Finishing the task of updating the user's address.", LOG_FINISH_TASK);
    }

    @Test
    @DisplayName("When log error task checked then success")
    void whenLogErrorTaskChecked_thenSuccess() {
        // Asserts
        assertEquals("[NEXTDNS -> TASKLET] An error occured while trying to run the task. Error: %s.", LOG_ERROR_TASK);
    }
}