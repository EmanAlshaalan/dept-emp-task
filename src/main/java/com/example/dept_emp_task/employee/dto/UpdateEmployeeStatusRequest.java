package com.example.dept_emp_task.employee.dto;

import com.example.dept_emp_task.employee.entity.EmployeeStatus;

import jakarta.validation.constraints.NotNull;

public class UpdateEmployeeStatusRequest {
 @NotNull
  private EmployeeStatus status;

 public EmployeeStatus getStatus() {
    return status;
 }

 public void setStatus(EmployeeStatus status) {
    this.status = status;
 }

}
