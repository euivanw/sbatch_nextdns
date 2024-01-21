package dev.ivanwilhelm.nextdns.exception;

public class SchedulerSetupFailException extends RuntimeException {
    public SchedulerSetupFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
