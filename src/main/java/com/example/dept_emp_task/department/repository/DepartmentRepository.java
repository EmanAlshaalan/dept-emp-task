package com.example.dept_emp_task.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dept_emp_task.department.entity.Department;


//the extends Jpa.. automatically will give me save() findById() findAll() deleteById() existsById() count()
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    boolean existsByNameIgnoreCase(String name);

}
