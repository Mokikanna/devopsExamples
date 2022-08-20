package com.kiran.departmentservice.service;

import com.kiran.departmentservice.entity.Department;
import com.kiran.departmentservice.exception.ResourceNotFoundException;
import com.kiran.departmentservice.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment of DepartmentService");
        return departmentRepository.save(department);
    }

    public Department findDepartmentById(Long departmentId) {
        log.info("Inside saveDepartment of DepartmentService");
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("department  not found with id;"+ departmentId));
    }

}
