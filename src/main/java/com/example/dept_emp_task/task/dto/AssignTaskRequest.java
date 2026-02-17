package com.example.dept_emp_task.task.dto;

import java.util.List;

public class AssignTaskRequest {

    private List<Long> employeeIds;

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    
}
