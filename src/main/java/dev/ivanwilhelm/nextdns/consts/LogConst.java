package dev.ivanwilhelm.nextdns.consts;

public abstract class LogConst {
    public static final String LOG_JOB_INTERVAL = "[NEXTDNS -> SCHEDULER] The task will run every %s seconds.";
    public static final String LOG_UPDATE = "[NEXTDNS -> REPOSITORY] Update occurred successfully. API response: %s.";
    public static final String LOG_START_TASK = "[NEXTDNS -> TASKLET] Starting the user's address update task.";
    public static final String LOG_FINISH_TASK = "[NEXTDNS -> TASKLET] Finishing the task of updating the user's address.";
    public static final String LOG_ERROR_TASK = "[NEXTDNS -> TASKLET] An error occured while trying to run the task. Error: %s.";

    private LogConst() {
    }
}
