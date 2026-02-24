package com.example.dept_emp_task.task.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.dept_emp_task.common.exception.NotFoundException;
import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;
import com.example.dept_emp_task.task.entity.Task;
import com.example.dept_emp_task.task.entity.TaskStatus;
import com.example.dept_emp_task.task.repository.EmployeeTaskRepository;
import com.example.dept_emp_task.task.repository.TaskRepository;
import com.example.dept_emp_task.task.service.Impl.EmployeeTaskServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeTaskServiceImplTest {

    @Mock
    EmployeeTaskRepository employeeTaskRepo;

    @Mock
    TaskRepository taskRepo;

    @InjectMocks
    EmployeeTaskServiceImpl service;

    @Test
    void updateStatus_whenAllDone_setsTaskDone() {
        Long employeeId = 1L;
        Long taskId = 10L;

        EmployeeTask et = new EmployeeTask();
        Task task = new Task();
        task.setId(taskId);
        task.setStatus(TaskStatus.IN_PROGRESS);

        when(employeeTaskRepo.findByEmployeeIdAndTaskId(employeeId, taskId))
                .thenReturn(Optional.of(et));

        when(employeeTaskRepo.countByTaskId(taskId)).thenReturn(2L);
        when(employeeTaskRepo.countByTaskIdAndStatus(taskId, EmployeeTaskStatus.DONE)).thenReturn(2L);

        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));

        service.updateStatus(employeeId, taskId, EmployeeTaskStatus.DONE);

        assertEquals(EmployeeTaskStatus.DONE, et.getStatus());
        assertEquals(TaskStatus.DONE, task.getStatus());

        verify(taskRepo).save(task);
    }

    @Test
    void updateStatus_whenNotAllDoneAndHasProgress_setsTaskInProgress() {
        Long employeeId = 1L;
        Long taskId = 10L;

        EmployeeTask et = new EmployeeTask();
        Task task = new Task();
        task.setId(taskId);
        task.setStatus(TaskStatus.NOT_STARTED);

        when(employeeTaskRepo.findByEmployeeIdAndTaskId(employeeId, taskId))
                .thenReturn(Optional.of(et));

        when(employeeTaskRepo.countByTaskId(taskId)).thenReturn(3L);
        when(employeeTaskRepo.countByTaskIdAndStatus(taskId, EmployeeTaskStatus.DONE)).thenReturn(1L);

        when(employeeTaskRepo.existsByTaskIdAndStatus(taskId, EmployeeTaskStatus.IN_PROGRESS)).thenReturn(true);

        when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));

        service.updateStatus(employeeId, taskId, EmployeeTaskStatus.IN_PROGRESS);

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        verify(taskRepo).save(task);
    }

    @Test
    void updateStatus_whenAssignmentNotFound_throwsNotFound() {
        when(employeeTaskRepo.findByEmployeeIdAndTaskId(1L, 10L))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                service.updateStatus(1L, 10L, EmployeeTaskStatus.DONE)
        );
    }
}