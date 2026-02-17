package com.example.dept_emp_task.task.dto;

import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;

public class UpdateEmployeeTaskStatusRequest {
private EmployeeTaskStatus status;

public EmployeeTaskStatus getStatus() {
    return status;
}

public void setStatus(EmployeeTaskStatus status) {
    this.status = status;
}


}
