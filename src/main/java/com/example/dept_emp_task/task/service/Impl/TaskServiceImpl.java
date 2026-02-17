package com.example.dept_emp_task.task.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dept_emp_task.common.exception.NotFoundException;
import com.example.dept_emp_task.employee.repository.EmployeeRepository;
import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;
import com.example.dept_emp_task.task.entity.Task;
import com.example.dept_emp_task.task.repository.EmployeeTaskRepository;
import com.example.dept_emp_task.task.repository.TaskRepository;
import com.example.dept_emp_task.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepo;
    private final EmployeeRepository employeeRepo;
    private final EmployeeTaskRepository employeeTaskRepo;

    public TaskServiceImpl(TaskRepository taskRepo, EmployeeRepository employeeRepo,
            EmployeeTaskRepository employeeTaskRepo) {
        this.taskRepo = taskRepo;
        this.employeeRepo = employeeRepo;
        this.employeeTaskRepo = employeeTaskRepo;
    }

    @Override
    public Task create(String name, LocalDate startDate, LocalDate endDate, Integer duration) {
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

}
