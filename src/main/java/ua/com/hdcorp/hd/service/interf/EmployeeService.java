package ua.com.hdcorp.hd.service.interf;


import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.dto.EmployeeDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDto registerNewEmployee(Employee employee);

    Employee findByUsername(String username);

    EmployeeDto findEmployeeById(Long id);

    Employee getEmployeeById(Long id);

    List<EmployeeDto> getEmployees();

    EmployeeDto deleteEmployee(Long id);

    EmployeeDto updateEmployee(Long id, Employee employeePatch);
}
