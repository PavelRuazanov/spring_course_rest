package com.pavelruazanov.spring.rest.controller;

import com.pavelruazanov.spring.rest.entity.Employee;
import com.pavelruazanov.spring.rest.exception_handling.EmployeeIncorrectData;
import com.pavelruazanov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.pavelruazanov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> shoeAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("Работника с ID = " + id + " не существует в базе");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployees(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee changeEmployees(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping(value = "/employees/{id}", produces = "text/html; charset=utf-8")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("Сотрудника с ID "
            + id + " не существует");
        }
        employeeService.deleteEmployee(id);
        return "Работник с ID = " + id + " удален";
    }
}
