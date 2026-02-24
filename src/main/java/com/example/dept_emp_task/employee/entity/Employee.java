package com.example.dept_emp_task.employee.entity;

import com.example.dept_emp_task.department.entity.Department;
import jakarta.persistence.*;

@Entity
@Table(name = "employees",
      uniqueConstraints = @UniqueConstraint(name="uk_employees_email", columnNames="email")

)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department; 

    public Employee() {}

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
        this.status = EmployeeStatus.ACTIVE;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public EmployeeStatus getStatus() { return status; }
    public Department getDepartment() { return department; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setStatus(EmployeeStatus status) { this.status = status; }
    public void setDepartment(Department department) { this.department = department; }
}