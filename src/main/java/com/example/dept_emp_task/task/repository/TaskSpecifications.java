package com.example.dept_emp_task.task.repository;

import org.springframework.data.jpa.domain.Specification;

import com.example.dept_emp_task.task.entity.Task;
import com.example.dept_emp_task.task.entity.TaskStatus;
import com.example.dept_emp_task.task.entity.EmployeeTask;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class TaskSpecifications {

    public static Specification<Task> nameContains(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank())
                return null;
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
        //WHERE LOWER(t.name) LIKE '%report%'
    }

    public static Specification<Task> statusIs(TaskStatus status) {
        return (root, query, cb) -> {
            if (status == null)
                return null;
            return cb.equal(root.get("status"), status);
        };
        //WHERE t.status = 'DONE'
    }

    public static Specification<Task> assignedToEmployee(Long employeeId) {
        return (root, query, cb) -> {
            if (employeeId == null)
                return null;

            query.distinct(true);

            Join<Task, EmployeeTask> assignmentJoin = root.join("assignments", JoinType.INNER);

            return cb.equal(assignmentJoin.get("employee").get("id"), employeeId);
        };
    }

    public static Specification<Task> assignedToDepartment(Long departmentId) {
        return (root, query, cb) -> {
            if (departmentId == null)
                return null;

            query.distinct(true);

            Join<Task, EmployeeTask> assignmentJoin = root.join("assignments", JoinType.INNER);

            return cb.equal(
                    assignmentJoin.get("employee").get("department").get("id"),
                    departmentId);
        };
    }
}