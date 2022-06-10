package com.example.SpringBootWithMySQL.service;

import com.example.SpringBootWithMySQL.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long id);
}
