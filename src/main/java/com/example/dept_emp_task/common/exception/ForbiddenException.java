package com.example.dept_emp_task.common.exception;


public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}