package com.example.dept_emp_task.task.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.dept_emp_task.task.entity.TaskStatus;

public class TaskSearchResponse {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer duration;
    private TaskStatus status;
    private List<AssigneeDTO> assignees;

    public TaskSearchResponse() {}

    public TaskSearchResponse(Long id, String name, LocalDate startDate, LocalDate endDate,
                              Integer duration, TaskStatus status, List<AssigneeDTO> assignees) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.status = status;
        this.assignees = assignees;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public List<AssigneeDTO> getAssignees() { return assignees; }
    public void setAssignees(List<AssigneeDTO> assignees) { this.assignees = assignees; }
}