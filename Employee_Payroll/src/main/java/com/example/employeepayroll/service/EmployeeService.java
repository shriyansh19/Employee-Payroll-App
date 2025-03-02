package com.example.employeepayroll.service;

import com.example.employeepayroll.dto.EmployeeDTO;
import com.example.employeepayroll.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    // In-memory storage for employees
    private List<Employee> employeeList = new ArrayList<>();

    // AtomicLong to generate unique IDs for employees
    private final AtomicLong idGenerator = new AtomicLong();

    /**
     * Create an employee and add it to the in-memory list.
     *
     * @param employeeDTO Data Transfer Object containing employee details.
     * @return Created Employee object.
     */
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        long id = idGenerator.incrementAndGet(); // Generate unique ID
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());

        employeeList.add(employee); // Add employee to the in-memory list
        return employee;
    }

    /**
     * Retrieve all employees from the in-memory list.
     *
     * @return List of all employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    /**
     * Retrieve an employee by ID from the in-memory list.
     *
     * @param id Employee ID to search for.
     * @return Optional containing the employee if found, otherwise empty.
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeList.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    /**
     * Update an employee's details in the in-memory list.
     *
     * @param id          Employee ID to update.
     * @param employeeDTO Updated employee details.
     * @return Updated Employee object if found, otherwise null.
     */
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            return employee;
        }
        return null; // Return null if employee not found
    }

    /**
     * Delete an employee from the in-memory list.
     *
     * @param id Employee ID to delete.
     */
    public void deleteEmployee(Long id) {
        employeeList.removeIf(employee -> employee.getId().equals(id));
    }
}