package ua.com.hdcorp.hd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.DuplicateFoundException;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.EmployeeRepository;
import ua.com.hdcorp.hd.service.interf.EmployeeService;
import ua.com.hdcorp.hd.service.interf.RoleService;
import ua.com.hdcorp.hd.service.util.EmployeeHelper;

import java.util.List;
import java.util.Optional;

import static ua.com.hdcorp.hd.utils.Constants.DUPLICATE_EMAIL;
import static ua.com.hdcorp.hd.utils.Constants.ROLE_NOT_FOUND;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmployeeHelper employeeHelper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder, EmployeeHelper employeeHelper) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.employeeHelper = employeeHelper;
    }

    @Override
    public Employee registerNewEmployee(Employee employee) {
        Optional<Role> role = roleService.findById(employee.getRole().getId());
        role.orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));

        if (findByUsername(employee.getEmail()) != null) {
            throw new DuplicateFoundException(DUPLICATE_EMAIL);
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(role.get());
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
}
