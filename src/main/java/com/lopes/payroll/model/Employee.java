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
     * The First name.
     */
    private String firstName;
    /**
     * The Last name.
     */
    private String lastName;
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
     * @param firstName the first name
     * @param lastName  the last name
     * @param role      the role
     */
    public Employee(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

}
