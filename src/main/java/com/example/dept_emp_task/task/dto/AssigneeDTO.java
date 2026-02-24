package com.example.dept_emp_task.task.dto;

import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;

public class AssigneeDTO {
    private Long employeeId;
    private String employeeName;
    private String employeeEmail;
    private EmployeeTaskStatus employeeTaskStatus;
    public AssigneeDTO() {
    }
    public AssigneeDTO(Long employeeId, String employeeName, String employeeEmail,
            EmployeeTaskStatus employeeTaskStatus) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeTaskStatus = employeeTaskStatus;
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getEmployeeEmail() {
        return employeeEmail;
    }
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
    public EmployeeTaskStatus getEmployeeTaskStatus() {
        return employeeTaskStatus;
    }
    public void setEmployeeTaskStatus(EmployeeTaskStatus employeeTaskStatus) {
        this.employeeTaskStatus = employeeTaskStatus;
    }


}
