package com.jpablobv.departmentservice.service.impl;

import com.jpablobv.departmentservice.dto.DepartmentDto;
import com.jpablobv.departmentservice.entity.Department;
import com.jpablobv.departmentservice.exception.ResourceNotFoundException;
import com.jpablobv.departmentservice.mapper.IAutoDepartmentMapper;
import com.jpablobv.departmentservice.repository.IDepartmentRepository;
import com.jpablobv.departmentservice.service.IDepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {

    private IDepartmentRepository departmentRepository;

    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // Department department = DepartmentMapper.mapToDepartment(departmentDto);
        //Department department = modelMapper.map(departmentDto, Department.class);
        Department department = IAutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);

        Department saveDepartment = departmentRepository.save(department);

        // return DepartmentMapper.mapToDepartmentDto(saveDepartment);
        // return modelMapper.map(saveDepartment, DepartmentDto.class);
        return IAutoDepartmentMapper.MAPPER.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDeparmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        if (department == null) {
            throw new ResourceNotFoundException("Department", "code", departmentCode);
        }
        // return DepartmentMapper.mapToDepartmentDto(department);
        // return modelMapper.map(department, DepartmentDto.class);
        return IAutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
            () -> new ResourceNotFoundException("Department", "id", departmentId.toString())
        );
        // return modelMapper.map(department, DepartmentDto.class);
        return IAutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
/*
        .map(DepartmentMapper::mapToDepartmentDto) // .map((department) -> DepartmentMapper.mapToDepartmentDto(department))
        .map(department -> modelMapper.map(department, DepartmentDto.class))
*/
        return departments.stream()
                .map(IAutoDepartmentMapper.MAPPER::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", departmentId.toString())
        );

        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setDepartmentCode(departmentDto.getDepartmentCode());

        Department updateDepartment = departmentRepository.save(department);

        // return modelMapper.map(updateDepartment, DepartmentDto.class);
        return IAutoDepartmentMapper.MAPPER.mapToDepartmentDto(updateDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department", "id", departmentId.toString())
        );
        departmentRepository.delete(department);
    }

}
