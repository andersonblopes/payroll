package com.lopes.payroll.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Employee not found advice.
 */
@ControllerAdvice
public class EmployeeNotFoundAdvice {

    /**
     * Employee not found handler string.
     *
     * @param employeeNotFoundException the employee not found exception
     * @return the string
     */
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException employeeNotFoundException) {
        return employeeNotFoundException.getMessage();
    }
}
