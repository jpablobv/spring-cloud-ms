package com.jpablobv.employeeservice.service.impl;

import com.jpablobv.employeeservice.dto.EmployeeDto;
import com.jpablobv.employeeservice.entity.Employee;
import com.jpablobv.employeeservice.exception.ResourceNotFoundException;
import com.jpablobv.employeeservice.mapper.IAutoEmployeeMapper;
import com.jpablobv.employeeservice.repository.IEmployeeRepository;
import com.jpablobv.employeeservice.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        // Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee employee = IAutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        //return EmployeeMapper.mapToEmployeeDto(savedEmployee);
        // return modelMapper.map(savedEmployee, EmployeeDto.class);
        return IAutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

/*        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect( Collectors.toList() );*/

        return employees.stream()
                .map(IAutoEmployeeMapper.MAPPER::mapToEmployeeDto)
                .collect( Collectors.toList() );
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );

        // return EmployeeMapper.mapToEmployeeDto(employee);
        // return modelMapper.map(employee, EmployeeDto.class);
        return IAutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee savedEmployee = employeeRepository.save(employee);

        //return EmployeeMapper.mapToEmployeeDto(savedEmployee);
        // return modelMapper.map(savedEmployee, EmployeeDto.class);
        return IAutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );
        employeeRepository.delete(employee);
    }

}
