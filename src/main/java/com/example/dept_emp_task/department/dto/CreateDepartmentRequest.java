package com.example.dept_emp_task.department.dto;

public class CreateDepartmentRequest {
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
