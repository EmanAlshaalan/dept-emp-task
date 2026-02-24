package com.example.dept_emp_task.employee.dto;

import com.example.dept_emp_task.employee.entity.EmployeeStatus;
import jakarta.validation.constraints.Email;

public class UpdateEmployeeRequest {

    @Email(message = "Invalid email")
    private String email;

    private String name;

    private EmployeeStatus status;

    public String getEmail() { return email; }
    public String getName() { return name; }
    public EmployeeStatus getStatus() { return status; }
}