package com.example.dept_emp_task.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dept_emp_task.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

        boolean existsByEmail(String email);
        List <Employee> findByDepartmentId(Long departmentId);
        


}
