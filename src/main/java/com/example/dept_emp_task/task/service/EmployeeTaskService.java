package com.example.dept_emp_task.task.service;

import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;

public interface EmployeeTaskService {
    void updateStatus(Long employeeId, Long taskId, EmployeeTaskStatus status);

}
