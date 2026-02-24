package com.example.dept_emp_task.task.dto;

import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;

public interface StatusCountView {
    EmployeeTaskStatus getStatus();
    long getCount();

}
