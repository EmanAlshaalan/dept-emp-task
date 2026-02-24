package com.example.dept_emp_task.employee.dto;

public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private String status;
    private Long departmentId;
    private String departmentName;




    
    public EmployeeResponse(Long id, String name, String email, String status, Long departmentId,
            String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
