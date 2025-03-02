package com.example.employeepayroll.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle validation errors for REST API requests.
     *
     * @param ex MethodArgumentNotValidException thrown during validation.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handle custom exceptions for Employee Payroll App.
     *
     * @param ex Custom exception thrown during processing.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(EmployeePayrollException.class)
    public ResponseEntity<String> handleEmployeePayrollException(EmployeePayrollException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}