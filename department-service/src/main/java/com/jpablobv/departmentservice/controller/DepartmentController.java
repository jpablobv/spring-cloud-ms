package com.jpablobv.departmentservice.controller;

import com.jpablobv.departmentservice.dto.DepartmentDto;
import com.jpablobv.departmentservice.exception.ErrorDetails;
import com.jpablobv.departmentservice.exception.ResourceNotFoundException;
import com.jpablobv.departmentservice.service.IDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private IDepartmentService departmentService;

    // Build save department REST API
    @PostMapping
    public ResponseEntity<?> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto saveDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    // Build get department By Code REST API
    @GetMapping("code/{department-code}")
    public ResponseEntity<?> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDeparmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    // Build get all Departments REST API
    @GetMapping
    public ResponseEntity<?> getAllDepartments(){
        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentDtos, HttpStatus.OK);
    }

    // Build get Department By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDto);
    }

    // Build update Department REST API
    @PutMapping("{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto departmentDto) {
        DepartmentDto updateDepartmentDto = departmentService.updateDepartment(departmentId, departmentDto);
        return ResponseEntity.ok(updateDepartmentDto);
    }

    // Build delete Department By ID REST API
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department deleted successfully!");
    }

/*    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "DEPARTMENT_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/

}
