package com.example.dept_emp_task.task.service;

import java.time.LocalDate;
import java.util.List;

import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.Task;

public interface TaskService {
    Task create(String name, LocalDate startDate, LocalDate endDate, Integer duration);
    void assign(Long taskId, List<Long> employeeIds);
    List<EmployeeTask> getAssignments(Long taskId);

}
