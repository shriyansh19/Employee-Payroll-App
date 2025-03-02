package com.example.employeepayroll.exception;

import org.springframework.http.HttpStatus;

// Custom Exception Class
public class EmployeePayrollException extends RuntimeException {

    private final HttpStatus status;

    // Constructor to initialize the exception with a message and HTTP status
    public EmployeePayrollException(String message, HttpStatus status) {
        super(message); // Pass the message to the parent class (RuntimeException)
        this.status = status; // Store the HTTP status
    }

    // Getter for the HTTP status
    public HttpStatus getStatus() {
        return status;
    }
}