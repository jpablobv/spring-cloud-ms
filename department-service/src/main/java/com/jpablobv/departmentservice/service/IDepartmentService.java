package com.jpablobv.departmentservice.service;

import com.jpablobv.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface IDepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDeparmentByCode(String code);

    DepartmentDto getDepartmentById(Long departmentId);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto);

    void deleteDepartment(Long departmentId);

}
