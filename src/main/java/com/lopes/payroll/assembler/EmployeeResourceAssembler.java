package com.lopes.payroll.assembler;

import com.lopes.payroll.controller.EmployeeController;
import com.lopes.payroll.model.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Employee resource assembler.
 */
@Component
public class EmployeeResourceAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    /**
     * To model entity model.
     *
     * @param employee the employee
     * @return the entity model
     */
    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        return new EntityModel<>(employee,
                linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

}
