package com.jpablobv.departmentservice.mapper;

import com.jpablobv.departmentservice.dto.DepartmentDto;
import com.jpablobv.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAutoDepartmentMapper {

    IAutoDepartmentMapper MAPPER = Mappers.getMapper(IAutoDepartmentMapper.class);

    Department mapToDepartment(DepartmentDto departmentDto);

    DepartmentDto mapToDepartmentDto(Department department);

}
