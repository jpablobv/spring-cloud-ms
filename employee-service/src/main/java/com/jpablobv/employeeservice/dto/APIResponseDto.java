package com.jpablobv.employeeservice.dto;

import com.jpablobv.employeeservice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {

    private EmployeeDto employee;
    private DepartmentDto department;

}
