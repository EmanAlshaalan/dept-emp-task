package com.example.dept_emp_task.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateDepartmentRequest {
    @NotBlank(message = "Department name is required")
    @Size(min = 2, max = 50, message = "Department name must be 2-50 characters")
    private String name;

    public CreateDepartmentRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
