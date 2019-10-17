package ua.com.hdcorp.hd.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.dto.EmployeeDto;

@Component
public class EmployeeHelper {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeHelper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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

    public void patchEmployee(Employee employeeToPatch, Employee employee) {
        if(employee.getFirstName() != null){
            employeeToPatch.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName() != null){
            employeeToPatch.setLastName(employee.getLastName());
        }
        if(employee.getAddress() != null){
            employeeToPatch.setAddress(employee.getAddress());
        }
        if(employee.getPhone() != null){
            employeeToPatch.setPhone(employee.getPhone());
        }
        if(employee.getEmail() != null){
            employeeToPatch.setEmail(employee.getEmail());
        }
        if(employee.getPassword() != null){
            employeeToPatch.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        if(employee.getRole().getId() != null){
            employeeToPatch.setRole(employee.getRole());
        }
    }
}
