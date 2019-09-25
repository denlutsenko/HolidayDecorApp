package ua.com.hdcorp.hd.service.interf;


import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    Employee registerNewEmployee(Employee employee);
    Employee findByUsername(String username);
    List<Employee> getAllEmployees();
}
