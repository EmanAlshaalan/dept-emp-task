package com.example.dept_emp_task.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dept_emp_task.task.dto.StatusCountView;
import com.example.dept_emp_task.task.entity.EmployeeTask;
import com.example.dept_emp_task.task.entity.EmployeeTaskStatus;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {
    boolean existsByEmployeeIdAndTaskId(Long employeeId, Long taskId);

    Optional<EmployeeTask> findByEmployeeIdAndTaskId(Long employeeId, Long taskId);

    long countByTaskId(Long taskId);

    long countByTaskIdAndStatus(Long taskId, EmployeeTaskStatus status);

    boolean existsByTaskIdAndStatus(Long taskId, EmployeeTaskStatus status);

    List<EmployeeTask> findByTaskId(Long taskId);

    @Query("""
                select et.status as status, count(et) as count
                from EmployeeTask et
                where et.employee.id = :employeeId
                group by et.status
            """)
    List<StatusCountView> countByStatusForEmployee(@Param("employeeId") Long employeeId);

    @Query("""
                select et.status as status, count(et) as count
                from EmployeeTask et
                where et.employee.department.id = :departmentId
                group by et.status
           """)
    List<StatusCountView> countByStatusForDepartment(@Param("departmentId") Long departmentId);
}
