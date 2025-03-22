package pt.ua.deti.ies.lab3_2.service;

import pt.ua.deti.ies.lab3_2.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    Optional<Employee> getEmployeeByEmail(String email);

    List<Employee> getEmployeesByEmail(String email);
}
