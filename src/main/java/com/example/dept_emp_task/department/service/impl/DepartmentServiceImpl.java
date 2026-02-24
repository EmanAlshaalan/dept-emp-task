package com.example.dept_emp_task.department.service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.dept_emp_task.common.exception.ConflictException;
import com.example.dept_emp_task.common.exception.NotFoundException;
import com.example.dept_emp_task.department.dto.DepartmentResponse;
import com.example.dept_emp_task.department.dto.UpdateDepartmentRequest;
import com.example.dept_emp_task.department.entity.Department;
import com.example.dept_emp_task.department.entity.DepartmentStatus;
import com.example.dept_emp_task.department.repository.DepartmentRepository;
import com.example.dept_emp_task.department.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repo;

    public DepartmentServiceImpl(DepartmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Department create(String name) {
          if (repo.existsByNameIgnoreCase(name)) {
            throw new ConflictException("name already exisit");
        }
        return repo.save(new Department(name));
    }

    @Override
    public List<Department> findAll() {
        return repo.findAll();
    }

    @Override
    public Department deactivate(Long id) {
        Department d = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found"));

        d.setStatus(DepartmentStatus.INACTIVE);
        return repo.save(d);
    }

    @Override
public DepartmentResponse update(Long id, UpdateDepartmentRequest req) {

    Department department = repo.findById(id)
            .orElseThrow(() -> new NotFoundException("Department not found"));

    if (req.getName() != null) {
        department.setName(req.getName().trim());
    }

    if (req.getStatus() != null) {
        department.setStatus(req.getStatus());
    }

    Department saved = repo.save(department);

    return new DepartmentResponse(
            saved.getId(),
            saved.getName(),
            saved.getStatus().name()
    );
}
}
