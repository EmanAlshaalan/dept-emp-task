package com.example.dept_emp_task.task.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dept_emp_task.task.dto.TaskSearchResponse;
import com.example.dept_emp_task.task.dto.TaskStatusStatsResponse;
import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.Task;
import com.example.dept_emp_task.task.entity.TaskStatus;

public interface TaskService {
    Task create(String name, LocalDate startDate, LocalDate endDate, Integer duration);

    void assign(Long taskId, List<Long> employeeIds);

    List<EmployeeTask> getAssignments(Long taskId);

    Page<TaskSearchResponse> search(Long employeeId, Long departmentId, TaskStatus status, String name, Pageable pageable);
TaskStatusStatsResponse statsByEmployee(Long employeeId);
TaskStatusStatsResponse statsByDepartment(Long departmentId);


}
