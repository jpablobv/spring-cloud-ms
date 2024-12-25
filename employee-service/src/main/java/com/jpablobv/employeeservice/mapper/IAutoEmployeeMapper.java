package com.jpablobv.employeeservice.mapper;

import com.jpablobv.employeeservice.dto.EmployeeDto;
import com.jpablobv.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAutoEmployeeMapper {

    IAutoEmployeeMapper MAPPER = Mappers.getMapper(IAutoEmployeeMapper.class);

    Employee mapToEmployee(EmployeeDto employeeDto);

    EmployeeDto mapToEmployeeDto(Employee employee);

}
