package com.example.dept_emp_task.department.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dept_emp_task.department.dto.CreateDepartmentRequest;
import com.example.dept_emp_task.department.dto.DepartmentResponse;
import com.example.dept_emp_task.department.dto.UpdateDepartmentRequest;
import com.example.dept_emp_task.department.entity.Department;
import com.example.dept_emp_task.department.service.DepartmentService;
import com.example.dept_emp_task.employee.entity.Employee;
import com.example.dept_emp_task.employee.service.EmployeeService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService service, EmployeeService employeeService) {
        this.service = service;
        this.employeeService = employeeService;
    }

    @PostMapping
    public Department create(@Valid @RequestBody CreateDepartmentRequest request) {
        return service.create(request.getName());
    }

    @GetMapping
    public List<Department> getAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateDepartmentRequest req) {

        return ResponseEntity.ok(service.update(id, req));
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long id) {
        return employeeService.getByDepartment(id);
    }

}
