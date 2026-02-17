package com.example.dept_emp_task.department.entity;
import jakarta.persistence.*;

@Entity
@Table(name= "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    public Department() {
    }



    public Department(String name) {
        this.name = name;
        this.status = DepartmentStatus.ACTIVE;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepartmentStatus status = DepartmentStatus.ACTIVE;


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


    public DepartmentStatus getStatus() {
        return status;
    }



    public void setStatus(DepartmentStatus status) {
        this.status = status;
    }


   


}
