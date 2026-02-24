package com.example.dept_emp_task.department.dto;

import com.example.dept_emp_task.department.entity.DepartmentStatus;

public class UpdateDepartmentRequest {

    private String name;
    private DepartmentStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentStatus getStatus() {
        return status;
    }

}
