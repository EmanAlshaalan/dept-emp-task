package com.example.dept_emp_task.task.entity;

import com.example.dept_emp_task.employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "employee_tasks", uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "task_id" }))
public class EmployeeTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonBackReference
    private Task task;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeTaskStatus status =EmployeeTaskStatus.NOT_STARTED;



    public EmployeeTask() {
    }



    public EmployeeTask(Long id, Employee employee, Task task, EmployeeTaskStatus status) {
        this.id = id;
        this.employee = employee;
        this.task = task;
        this.status = EmployeeTaskStatus.NOT_STARTED;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Employee getEmployee() {
        return employee;
    }



    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



    public Task getTask() {
        return task;
    }



    public void setTask(Task task) {
        this.task = task;
    }



    public EmployeeTaskStatus getStatus() {
        return status;
    }



    public void setStatus(EmployeeTaskStatus status) {
        this.status = status;
    }

    

    



}
