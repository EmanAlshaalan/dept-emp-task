package com.example.dept_emp_task.task.dto;

public class TaskStatusStatsResponse {
    private Long notStarted;
    private Long inProgress;
    private Long done;
    public TaskStatusStatsResponse(Long notStarted, Long inProgress, Long done) {
        this.notStarted = notStarted;
        this.inProgress = inProgress;
        this.done = done;
    }
    public Long getNotStarted() {
        return notStarted;
    }
    public Long getInProgress() {
        return inProgress;
    }
    public Long getDone() {
        return done;
    }
}
