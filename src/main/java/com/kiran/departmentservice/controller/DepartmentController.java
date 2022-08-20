package com.kiran.departmentservice.controller;

import com.kiran.departmentservice.entity.Department;
import com.kiran.departmentservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<Department>  saveDepartment(@Valid @RequestBody Department department) {
        log.info("Inside saveDepartment method of DepartmentController");
        Department department1= departmentService.saveDepartment(department);
        return new ResponseEntity<Department>(department1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside findDepartmentById method of DepartmentController");
        Department department2= departmentService.findDepartmentById(departmentId);
        return new ResponseEntity<Department>(department2, HttpStatus.OK);
    }

}
