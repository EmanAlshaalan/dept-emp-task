package com.example.dept_emp_task.department.service;

import java.util.List;

import com.example.dept_emp_task.department.entity.Department;

public interface DepartmentService {

    Department create(String name); // create dep

    List<Department> findAll(); // list all dep

    Department deactivate(Long id);

    Department update(Long id,String name);
}
