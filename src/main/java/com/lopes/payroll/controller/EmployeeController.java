package com.lopes.payroll.controller;

import com.lopes.payroll.assembler.EmployeeResourceAssembler;
import com.lopes.payroll.errors.EmployeeNotFoundException;
import com.lopes.payroll.model.Employee;
import com.lopes.payroll.model.repository.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Employee controller.
 */
@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeResourceAssembler employeeResourceAssembler;

    /**
     * Instantiates a new Employee controller.
     *
     * @param employeeRepository        the employee repository
     * @param employeeResourceAssembler the employee resource assembler
     */
    public EmployeeController(EmployeeRepository employeeRepository, EmployeeResourceAssembler employeeResourceAssembler) {
        this.employeeRepository = employeeRepository;
        this.employeeResourceAssembler = employeeResourceAssembler;
    }

    /**
     * All collection model.
     *
     * @return the collection model
     */
    @GetMapping
    public CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(employeeResourceAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    /**
     * New employee response entity.
     *
     * @param employee the employee
     * @return the response entity
     * @throws URISyntaxException the uri syntax exception
     */
    @PostMapping
    public ResponseEntity<?> newEmployee(@RequestBody Employee employee) throws URISyntaxException {
        EntityModel<Employee> entityModel = employeeResourceAssembler.toModel(employeeRepository.save(employee));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Find by id entity model.
     *
     * @param id the id
     * @return the entity model
     */
    @GetMapping("/{id}")
    public EntityModel<Employee> findById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return employeeResourceAssembler.toModel(employee);

    }

    /**
     * Update employee response entity.
     *
     * @param newEmployee the new employee
     * @param id          the id
     * @return the response entity
     * @throws URISyntaxException the uri syntax exception
     */
    @PutMapping("/{id}")
    ResponseEntity<?> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) throws URISyntaxException {

        Employee updatedEmployee = employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = employeeResourceAssembler.toModel(updatedEmployee);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /**
     * Delete employee response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
