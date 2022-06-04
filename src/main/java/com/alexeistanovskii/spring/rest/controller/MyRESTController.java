package com.alexeistanovskii.spring.rest.controller;

import com.alexeistanovskii.spring.rest.entity.Employee;
import com.alexeistanovskii.spring.rest.exception_handling.EmployeeIncorrectData;
import com.alexeistanovskii.spring.rest.exception_handling.NoSuchEmployeeException;
import com.alexeistanovskii.spring.rest.service.EmployeeService;
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
    public List<Employee> showAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return employeeList;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in database");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        if(employeeService.getEmployee(id)==null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in database");
        }
        String surname = employeeService.getEmployee(id).getSurname();
        employeeService.deleteEmployee(id);

        return "Employee " + surname + " with id: " + id + " was deleted";
    }
// Adding random comment
}
