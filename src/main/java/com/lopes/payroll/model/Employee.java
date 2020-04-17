package com.lopes.payroll.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The type Employee.
 */
@Data
@Entity
public class Employee {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Role.
     */
    private String role;

    /**
     * Instantiates a new Employee.
     */
    public Employee() {
    }

    /**
     * Instantiates a new Employee.
     *
     * @param name the name
     * @param role the role
     */
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

}
