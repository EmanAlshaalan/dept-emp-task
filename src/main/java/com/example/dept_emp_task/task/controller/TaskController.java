package com.example.dept_emp_task.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dept_emp_task.task.dto.AssignTaskRequest;
import com.example.dept_emp_task.task.dto.CreateTaskRequest;
import com.example.dept_emp_task.task.dto.TaskSearchResponse;
import com.example.dept_emp_task.task.dto.TaskStatusStatsResponse;
import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.Task;
import com.example.dept_emp_task.task.entity.TaskStatus;
import com.example.dept_emp_task.task.service.TaskService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


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

    
@GetMapping("/search")
public Page<TaskSearchResponse> search(
        @RequestParam(required = false) Long employeeId,
        @RequestParam(required = false) Long departmentId,
        @RequestParam(required = false) TaskStatus status,
        @RequestParam(required = false) String name,
        Pageable pageable
) {
    return service.search(employeeId, departmentId, status, name, pageable);
}
@GetMapping("/stats/employee/{employeeId}")
public ResponseEntity<TaskStatusStatsResponse> statsByEmployee(@PathVariable Long employeeId) {
    return ResponseEntity.ok(service.statsByEmployee(employeeId));
}

@GetMapping("/stats/department/{departmentId}")
public ResponseEntity<TaskStatusStatsResponse> statsByDepartment(@PathVariable Long departmentId) {
    return ResponseEntity.ok(service.statsByDepartment(departmentId));
}

}
