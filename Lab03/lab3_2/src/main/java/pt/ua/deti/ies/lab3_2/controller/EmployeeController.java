package pt.ua.deti.ies.lab3_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ua.deti.ies.lab3_2.entity.Employee;
import pt.ua.deti.ies.lab3_2.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String email) {
        List<Employee> employees;
        if (email != null && !email.isEmpty()) {
            Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
            employees = employee.map(List::of).orElseGet(List::of);
        } else {
            employees = employeeService.getAllEmployees();
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long userId, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(userId, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long userId){
        employeeService.deleteEmployee(userId);
        return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
    }

}
