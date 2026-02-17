package com.example.dept_emp_task.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dept_emp_task.task.dto.AssignTaskRequest;
import com.example.dept_emp_task.task.dto.CreateTaskRequest;
import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.Task;
import com.example.dept_emp_task.task.service.TaskService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task create(@RequestBody CreateTaskRequest request) {
        return service.create(
                request.getName(),
                LocalDate.parse(request.getStartDate()),
                LocalDate.parse(request.getEndDate()),
                request.getDuration());
    }

    @PostMapping("/{taskId}/assign")
    public ResponseEntity<?> assign(@PathVariable Long taskId,
            @RequestBody AssignTaskRequest req) {
        service.assign(taskId, req.getEmployeeIds());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{taskId}/assignments")
    public List<EmployeeTask> assignments(@PathVariable Long taskId) {
        return service.getAssignments(taskId);
    }

}
