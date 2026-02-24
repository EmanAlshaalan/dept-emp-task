package com.example.dept_emp_task.task.service.Impl;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dept_emp_task.common.exception.BadRequestException;
import com.example.dept_emp_task.common.exception.NotFoundException;
import com.example.dept_emp_task.department.repository.DepartmentRepository;
import com.example.dept_emp_task.employee.repository.EmployeeRepository;
import com.example.dept_emp_task.task.dto.AssigneeDTO;
import com.example.dept_emp_task.task.dto.StatusCountView;
import com.example.dept_emp_task.task.dto.TaskSearchResponse;
import com.example.dept_emp_task.task.dto.TaskStatusStatsResponse;
import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;
import com.example.dept_emp_task.task.entity.Task;
import com.example.dept_emp_task.task.entity.TaskStatus;
import com.example.dept_emp_task.task.repository.EmployeeTaskRepository;
import com.example.dept_emp_task.task.repository.TaskRepository;
import com.example.dept_emp_task.task.service.TaskService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static com.example.dept_emp_task.task.repository.TaskSpecifications.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepo;
    private final EmployeeRepository employeeRepo;
    private final EmployeeTaskRepository employeeTaskRepo;
    private final DepartmentRepository departmentRepo;

    public TaskServiceImpl(TaskRepository taskRepo, EmployeeRepository employeeRepo,
            EmployeeTaskRepository employeeTaskRepo, DepartmentRepository departmentRepo) {
        this.taskRepo = taskRepo;
        this.employeeRepo = employeeRepo;
        this.employeeTaskRepo = employeeTaskRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public Task create(String name, LocalDate startDate, LocalDate endDate, Integer duration) {
        if (endDate.isBefore(startDate)) {
    throw new BadRequestException("endDate cannot be before startDate");
}
        Task task = new Task();
        task.setName(name);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setDuration(duration);
        return taskRepo.save(task);

    }

    @Override
    public void assign(Long taskId, List<Long> employeeIds) {
        var task = taskRepo.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));

        for (Long empId : employeeIds) {
            var emp = employeeRepo.findById(empId)
                    .orElseThrow(() -> new NotFoundException("Employee not found: " + empId));

            if (employeeTaskRepo.existsByEmployeeIdAndTaskId(empId, taskId))
                continue;

            var et = new EmployeeTask();
            et.setEmployee(emp);
            et.setTask(task);
            et.setStatus(EmployeeTaskStatus.NOT_STARTED);
            employeeTaskRepo.save(et);
        }
    }

    @Override
    public List<EmployeeTask> getAssignments(Long taskId) {
        taskRepo.findById(taskId).orElseThrow(() -> new NotFoundException("Task not found"));
        return employeeTaskRepo.findByTaskId(taskId);
    }

    @Override
    public Page<TaskSearchResponse> search(Long employeeId, Long departmentId, TaskStatus status, String name,
            Pageable pageable) {

        Specification<Task> spec = (root, query, cb) -> cb.conjunction();
        spec = spec.and(nameContains(name))
                .and(statusIs(status))
                .and(assignedToEmployee(employeeId))
                .and(assignedToDepartment(departmentId));
        Page<Task> result = taskRepo.findAll(spec, pageable);

        return result.map(task -> new TaskSearchResponse(
                task.getId(),
                task.getName(),
                task.getStartDate(),
                task.getEndDate(),
                task.getDuration(),
                task.getStatus(),
                task.getAssignments().stream()
                        .map(et -> new AssigneeDTO(
                                et.getEmployee().getId(),
                                et.getEmployee().getName(),
                                et.getEmployee().getEmail(),
                                et.getStatus()))
                        .collect(Collectors.toList())));

    }

    @Override
    public TaskStatusStatsResponse statsByEmployee(Long employeeId) {
        employeeRepo.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("employee not found"));

        List<StatusCountView> rows = employeeTaskRepo.countByStatusForEmployee(employeeId);
        return mapRowsToStats(rows);
    }

    @Override
    public TaskStatusStatsResponse statsByDepartment(Long departmentId) {
        departmentRepo.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("department not found"));

        List<StatusCountView> rows = employeeTaskRepo.countByStatusForDepartment(departmentId);

        return mapRowsToStats(rows);
    }

    private TaskStatusStatsResponse mapRowsToStats(List<StatusCountView> rows) {
        Map<EmployeeTaskStatus, Long> map = new EnumMap<>(EmployeeTaskStatus.class);

        for (StatusCountView r : rows) {
            map.put(r.getStatus(), r.getCount());
        }

        long notStarted = map.getOrDefault(EmployeeTaskStatus.NOT_STARTED, 0L);
        long inProgress = map.getOrDefault(EmployeeTaskStatus.IN_PROGRESS, 0L);
        long done = map.getOrDefault(EmployeeTaskStatus.DONE, 0L);

        return new TaskStatusStatsResponse(notStarted, inProgress, done);
    }
}
