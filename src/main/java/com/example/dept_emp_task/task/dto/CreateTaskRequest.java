package com.example.dept_emp_task.task.dto;

public class CreateTaskRequest {
    private String name;
    private String startDate;
    private String endDate;
    private Integer duration;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
