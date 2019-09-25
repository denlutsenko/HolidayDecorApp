package ua.com.hdcorp.hd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.EmployeeRepository;
import ua.com.hdcorp.hd.service.interf.EmployeeService;
import ua.com.hdcorp.hd.service.interf.RoleService;

import java.util.List;

import static ua.com.hdcorp.hd.exception.NotFoundException.Message.ROLE_NOT_FOUND;
import static ua.com.hdcorp.hd.utils.Constants.ROLE_NOT_FOUND_MSG;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee registerNewEmployee(Employee employee) {
        Role role = roleService.findByName(employee.getRoles().get(0).getName());

        if (roleService.isRoleEmpty(role)) {
            throw new NotFoundException(ROLE_NOT_FOUND, ROLE_NOT_FOUND_MSG);
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRoles(List.of(role));
        Employee savedEmployee = employeeRepository.save(employee);
        employeeRepository.refresh(savedEmployee);
        return savedEmployee;
    }

    @Override
    public Employee findByUsername(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

//    private EmployeeDto getEmployeeDto(Employee employee){
//        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId(employee.getId());
//        employeeDto.setFirstName(employee.getFirstName());
//        employeeDto.setLastName(employee.getLastName());
//        employeeDto.setAddress(employee.getAddress());
//        employeeDto.setPhone(employee.getPhone());
//        employeeDto.setRole(employee.getRoles().get(0).getName());
//        return employeeDto;
//    }
}
