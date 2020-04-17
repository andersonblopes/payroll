package com.lopes.payroll.controller;

import com.lopes.payroll.errors.EmployeeNotFoundException;
import com.lopes.payroll.model.Employee;
import com.lopes.payroll.model.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Employee controller.
 */
@RestController
@RequestMapping("employees")
public class EmployeeController {

    /**
     * The Employee repository.
     */
    private final EmployeeRepository employeeRepository;

    /**
     * Instantiates a new Employee controller.
     *
     * @param employeeRepository the employee repository
     */
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * All list.
     *
     * @return the list
     */
    @GetMapping
    List<Employee> all() {
        return employeeRepository.findAll();
    }

    /**
     * New employee employee.
     *
     * @param employee the employee
     * @return the employee
     */
    @PostMapping
    Employee newEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Find by id employee.
     *
     * @param id the id
     * @return the employee
     */
    @GetMapping("/{id}")
    Employee findById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /**
     * Update employee employee.
     *
     * @param newEmployee the new employee
     * @param id          the id
     * @return the employee
     */
    @PutMapping("/{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return employeeRepository.save(employee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return employeeRepository.save(newEmployee);
        });
    }


    /**
     * Delete employee.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

}
