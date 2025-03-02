package com.example.employeepayroll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Salary cannot be null")
    private Double salary;

    // Constructors
    public EmployeeDTO() {}

    public EmployeeDTO(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}