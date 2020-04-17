package com.lopes.payroll.errors;

/**
 * The type Employee not found exception.
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -8007158257209357207L;

    /**
     * Instantiates a new Employee not found exception.
     *
     * @param id the id
     */
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
