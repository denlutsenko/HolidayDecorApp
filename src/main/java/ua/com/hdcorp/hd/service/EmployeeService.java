package ua.com.hdcorp.hd.service;


import ua.com.hdcorp.hd.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee register(Employee employee);

    List<Employee> getAll();

    Employee findByUsername(String username);

    Employee findById(Long id);

    void delete(Long id);
}
