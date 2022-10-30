package glofox.task.interviewTask.controllers;

public class StatusResponse<T> {
    private final boolean success;
    private final T message;

    public StatusResponse(boolean success, T message) {
        this.success = success;
        this.message = message;
    }

    public T getMessage() {
        return message;
    }
    public boolean getSuccess() {
        return success;
    }
}
