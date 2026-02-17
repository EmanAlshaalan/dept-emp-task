package com.example.dept_emp_task.task.service.Impl;

import org.springframework.stereotype.Service;

import com.example.dept_emp_task.common.exception.NotFoundException;
import com.example.dept_emp_task.employee.repository.EmployeeRepository;
import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;
import com.example.dept_emp_task.task.entity.TaskStatus;
import com.example.dept_emp_task.task.repository.EmployeeTaskRepository;
import com.example.dept_emp_task.task.repository.TaskRepository;
import com.example.dept_emp_task.task.service.EmployeeTaskService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeTaskServiceImpl implements EmployeeTaskService {

    private final EmployeeTaskRepository employeeTaskRepo;
    private final TaskRepository taskRepo;

    public EmployeeTaskServiceImpl(EmployeeTaskRepository employeeTaskRepo, TaskRepository taskRepo) {
        this.employeeTaskRepo = employeeTaskRepo;
        this.taskRepo = taskRepo;
    }

    @Transactional
    @Override
    public void updateStatus(Long employeeId, Long taskId, EmployeeTaskStatus status) {
        var et = employeeTaskRepo.findByEmployeeIdAndTaskId(employeeId, taskId)
                .orElseThrow(() -> new NotFoundException("Assignment not found"));

                et.setStatus(status);
                employeeTaskRepo.save(et);
                recalcTaskStatus(taskId);
                

    }

    private void recalcTaskStatus(Long taskId) {
        long total = employeeTaskRepo.countByTaskId(taskId);
        if (total == 0) return;

        long doneCount = employeeTaskRepo.countByTaskIdAndStatus(taskId, EmployeeTaskStatus.DONE);

        var task = taskRepo.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task not found"));

        if (doneCount == total) {
            task.setStatus(TaskStatus.DONE);
        } else {
            boolean hasProgress =
                    employeeTaskRepo.existsByTaskIdAndStatus(taskId, EmployeeTaskStatus.IN_PROGRESS)
                 || employeeTaskRepo.existsByTaskIdAndStatus(taskId, EmployeeTaskStatus.DONE);

            task.setStatus(hasProgress ? TaskStatus.IN_PROGRESS : TaskStatus.NOT_STARTED);
        }

        taskRepo.save(task);
    }

}
