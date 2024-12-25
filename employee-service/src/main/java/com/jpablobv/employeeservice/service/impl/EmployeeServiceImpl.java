package com.jpablobv.employeeservice.service.impl;

import com.jpablobv.employeeservice.dto.APIResponseDto;
import com.jpablobv.employeeservice.dto.DepartmentDto;
import com.jpablobv.employeeservice.dto.EmployeeDto;
import com.jpablobv.employeeservice.entity.Employee;
import com.jpablobv.employeeservice.exception.ResourceNotFoundException;
import com.jpablobv.employeeservice.mapper.IAutoEmployeeMapper;
import com.jpablobv.employeeservice.repository.IEmployeeRepository;
import com.jpablobv.employeeservice.service.IAPIClient;
import com.jpablobv.employeeservice.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    // private RestTemplate restTemplate;
    // private WebClient webClient;
    private IAPIClient apiClient;

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
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", employeeId)
        );
/*        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
                "http://localhost:8080/api/departments/code/" + employee.getDepartmentCode(),
                DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();
*/
/*        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/code/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();*/

        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

        EmployeeDto employeeDto = IAutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        // return EmployeeMapper.mapToEmployeeDto(employee);
        // return modelMapper.map(employee, EmployeeDto.class);
        return apiResponseDto;
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
