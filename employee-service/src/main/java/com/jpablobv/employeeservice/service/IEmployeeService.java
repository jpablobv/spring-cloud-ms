package com.jpablobv.employeeservice.service;

import com.jpablobv.employeeservice.dto.APIResponseDto;
import com.jpablobv.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();

    APIResponseDto getEmployeeById(Long employeeId);

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);

    void deleteEmployee(Long employeeId);

}
