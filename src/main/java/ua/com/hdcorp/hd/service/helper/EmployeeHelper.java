package ua.com.hdcorp.hd.service.helper;

import org.springframework.stereotype.Component;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.dto.EmployeeDto;

@Component
public class EmployeeHelper {

    public EmployeeDto convertToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getAddress(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getRole()
        );
    }
}
