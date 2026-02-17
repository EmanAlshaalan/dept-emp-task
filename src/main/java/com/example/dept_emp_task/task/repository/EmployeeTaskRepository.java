package com.example.dept_emp_task.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {
    boolean existsByEmployeeIdAndTaskId(Long employeeId, Long taskId);

    Optional<EmployeeTask> findByEmployeeIdAndTaskId(Long employeeId, Long taskId);

    long countByTaskId(Long taskId);

    long countByTaskIdAndStatus(Long taskId, EmployeeTaskStatus status);

    boolean existsByTaskIdAndStatus(Long taskId, EmployeeTaskStatus status);
    List<EmployeeTask> findByTaskId(Long taskId);
}