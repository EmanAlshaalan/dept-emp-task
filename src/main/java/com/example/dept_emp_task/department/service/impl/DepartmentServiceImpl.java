package com.example.dept_emp_task.department.service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.dept_emp_task.common.exception.NotFoundException;
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
    public Department update(Long id, String name) {
      Department d = repo.findById(id).orElseThrow(()-> new NotFoundException("department not found"));

      d.setName(name);
      return repo.save(d);
    }

}
