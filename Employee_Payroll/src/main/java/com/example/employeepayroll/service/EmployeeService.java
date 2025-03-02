package com.example.employeepayroll.service;

import com.example.employeepayroll.dto.EmployeeDTO;
import com.example.employeepayroll.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class for managing employee data.
 */
@Service
@Slf4j // Enables logging using Lombok
public class EmployeeService {

    // List to store employee objects (simulating a database)
    private List<Employee> employeeList = new ArrayList<>();

    // Atomic counter to generate unique employee IDs
    private final AtomicLong idGenerator = new AtomicLong();

    /**
     * Creates a new employee and adds it to the list.
     * @param employeeDTO Data Transfer Object containing employee details.
     * @return The newly created Employee object.
     */
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        long id = idGenerator.incrementAndGet(); // Generate a unique ID
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        employeeList.add(employee); // Add employee to the list
        log.info("Employee created with ID: {}", id); // Log creation
        return employee;
    }

    /**
     * Retrieves all employees.
     * @return List of all Employee objects.
     */
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeList;
    }

    /**
     * Retrieves an employee by their ID.
     * @param id The unique ID of the employee.
     * @return An Optional containing the Employee object if found.
     */
    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeList.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    /**
     * Updates an existing employee's details.
     * @param id The unique ID of the employee.
     * @param employeeDTO The new details to update.
     * @return The updated Employee object, or null if not found.
     */
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            log.info("Employee updated with ID: {}", id); // Log update
            return employee;
        }
        log.warn("Employee not found with ID: {}", id); // Log warning
        return null;
    }

    /**
     * Deletes an employee by their ID.
     * @param id The unique ID of the employee.
     */
    public void deleteEmployee(Long id) {
        boolean removed = employeeList.removeIf(employee -> employee.getId().equals(id));
        if (removed) {
            log.info("Employee deleted with ID: {}", id); // Log deletion
        } else {
            log.warn("Employee not found with ID: {}", id); // Log warning
        }
    }
}
