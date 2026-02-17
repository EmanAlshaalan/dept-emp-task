package com.example.dept_emp_task.task.repository;

import com.example.dept_emp_task.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}