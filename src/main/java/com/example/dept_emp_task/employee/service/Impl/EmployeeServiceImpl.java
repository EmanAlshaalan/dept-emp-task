package com.example.dept_emp_task.employee.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dept_emp_task.common.exception.ConflictException;
import com.example.dept_emp_task.common.exception.ForbiddenException;
import com.example.dept_emp_task.common.exception.NotFoundException;
import com.example.dept_emp_task.department.entity.Department;
import com.example.dept_emp_task.department.entity.DepartmentStatus;
import com.example.dept_emp_task.department.repository.DepartmentRepository;
import com.example.dept_emp_task.employee.dto.EmployeeResponse;
import com.example.dept_emp_task.employee.dto.UpdateEmployeeRequest;
import com.example.dept_emp_task.employee.entity.Employee;
import com.example.dept_emp_task.employee.entity.EmployeeStatus;
import com.example.dept_emp_task.employee.repository.EmployeeRepository;
import com.example.dept_emp_task.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo, DepartmentRepository departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public Employee create(String email, String name) {
        if (employeeRepo.existsByEmail(email)) {
            throw new ConflictException("email already exisit");
        }

        return employeeRepo.save(new Employee(name, email));

    }

    @Override
    public Employee assignToDepartment(Long employyeId, Long departmentId) {
        Employee employee = employeeRepo.findById(employyeId)
                .orElseThrow(() -> new NotFoundException("employee not found"));

        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("department not found"));

        if (department.getStatus() == DepartmentStatus.INACTIVE) {
            throw new ForbiddenException("department is inactive");

        }

        employee.setDepartment(department);
        return employeeRepo.save(employee);

    }

    @Override
    public List<Employee> getByDepartment(Long departmentId) {
        return employeeRepo.findByDepartmentId(departmentId);
    }

    @Override
    public EmployeeResponse update(Long id, UpdateEmployeeRequest req) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("employee not found"));

        if (req.getEmail() != null) {
            String newEmail = req.getEmail().trim().toLowerCase();

            if (!employee.getEmail().equalsIgnoreCase(newEmail) && employeeRepo.existsByEmail(newEmail)) {
                throw new ConflictException("Email already exists");
            }
            employee.setEmail(newEmail);
        }

        if (req.getName() != null) {
            employee.setName(req.getName().trim());
        }

        if (req.getStatus() != null) {
            employee.setStatus(req.getStatus());
        }

        Employee saved = employeeRepo.save(employee);
        Long deptId = saved.getDepartment() != null ? saved.getDepartment().getId() : null;
        String deptName = saved.getDepartment() != null ? saved.getDepartment().getName() : null;

       
    return new EmployeeResponse(
            saved.getId(),
            saved.getName(),
            saved.getEmail(),
            saved.getStatus().name(),
            deptId,
            deptName
    );
    }



    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

}
