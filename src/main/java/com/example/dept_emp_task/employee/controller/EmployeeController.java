package com.example.dept_emp_task.employee.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dept_emp_task.employee.dto.CreateEmployeeRequest;
import com.example.dept_emp_task.employee.dto.UpdateEmployeeRequest;
import com.example.dept_emp_task.employee.entity.Employee;
import com.example.dept_emp_task.employee.service.EmployeeService;
import com.example.dept_emp_task.task.dto.UpdateEmployeeTaskStatusRequest;
import com.example.dept_emp_task.task.service.EmployeeTaskService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeTaskService employeeTaskService;

    public EmployeeController(EmployeeService service, EmployeeTaskService employeeTaskService) {
        this.service = service;
        this.employeeTaskService = employeeTaskService;
    }

    @PostMapping
    public Employee create(@RequestBody CreateEmployeeRequest request) {

        return service.create(request.getEmail(), request.getName());
    }

    @PutMapping("/{employeeId}/assign/{departmentId}")
    public Employee assign(
            @PathVariable Long employeeId,
            @PathVariable Long departmentId) {
        return service.assignToDepartment(employeeId, departmentId);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody UpdateEmployeeRequest request) {
        return service.update(id, request.getEmail(), request.getName());
    }

    @PatchMapping("/{id}/deactivate")
    public Employee deactivate(@PathVariable Long id) {
        return service.deactivate(id);

    }

    @GetMapping
    public List<Employee> findAll() {
        return service.findAll();
    }

    @PutMapping("/{employeeId}/tasks/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long employeeId,
            @PathVariable Long taskId,
            @RequestBody UpdateEmployeeTaskStatusRequest req) {
        employeeTaskService.updateStatus(employeeId, taskId, req.getStatus());
        return ResponseEntity.ok().build();
    }

}
