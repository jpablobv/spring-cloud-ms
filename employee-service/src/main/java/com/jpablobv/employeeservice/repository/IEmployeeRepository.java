package com.jpablobv.employeeservice.repository;

import com.jpablobv.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}
