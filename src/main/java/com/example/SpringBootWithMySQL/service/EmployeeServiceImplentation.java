package com.example.SpringBootWithMySQL.service;

import com.example.SpringBootWithMySQL.dao.EmployeeDao;
import com.example.SpringBootWithMySQL.exception.ResourceNotFoundException;
import com.example.SpringBootWithMySQL.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplentation implements EmployeeService{

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImplentation(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeDao.findById(id);
//        if (employee.isPresent()){
//            return employee.get();
//        }
//        else {
//            throw new ResourceNotFoundException("Employee","Id",id);
//        }

        return employeeDao.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Employee existingEmployee = employeeDao.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeDao.save(existingEmployee);
        return existingEmployee;

    }

    @Override
    public void deleteEmployee(long id) {
        //see whether employee exists in db or not
        employeeDao.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id));


        employeeDao.deleteById(id);
    }
}
