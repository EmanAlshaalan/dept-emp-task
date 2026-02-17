package com.example.dept_emp_task.employee.service;

import java.util.List;

import com.example.dept_emp_task.employee.entity.Employee;

public interface EmployeeService {

    Employee create(String email, String name);

    Employee assignToDepartment(Long employyeId, Long departmentId);

    List<Employee> getByDepartment(Long departmentId);

    Employee update(Long id, String email, String name);

    Employee deactivate(Long id);

    List<Employee> findAll();
}
