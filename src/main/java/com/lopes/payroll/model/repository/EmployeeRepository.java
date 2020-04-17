package com.lopes.payroll.model.repository;

import com.lopes.payroll.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Employee repository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
