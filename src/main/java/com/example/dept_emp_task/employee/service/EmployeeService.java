package com.example.dept_emp_task.employee.service;

import java.util.List;

import com.example.dept_emp_task.employee.dto.EmployeeResponse;
import com.example.dept_emp_task.employee.dto.UpdateEmployeeRequest;
import com.example.dept_emp_task.employee.entity.Employee;
import com.example.dept_emp_task.employee.entity.EmployeeStatus;

public interface EmployeeService {

    Employee create(String email, String name);

    Employee assignToDepartment(Long employyeId, Long departmentId);

    List<Employee> getByDepartment(Long departmentId);

EmployeeResponse update(Long id, UpdateEmployeeRequest req);
 
    List<Employee> findAll();

}
