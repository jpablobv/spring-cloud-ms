package com.jpablobv.employeeservice.controller;

import com.jpablobv.employeeservice.dto.EmployeeDto;
import com.jpablobv.employeeservice.exception.ErrorDetails;
import com.jpablobv.employeeservice.exception.ResourceNotFoundException;
import com.jpablobv.employeeservice.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private IEmployeeService employeeService;

    // Build create Employee REST API
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    // Build get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    // Build get all Employees REST API
    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
    }

    // Build update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeId, employeeDto);
        return ResponseEntity.ok(updatedEmployeeDto);
    }

    // Build delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted");
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
                "EMPLOYEE_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/

}
