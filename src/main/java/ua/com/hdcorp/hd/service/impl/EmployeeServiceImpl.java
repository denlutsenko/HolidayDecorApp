package ua.com.hdcorp.hd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.EmployeeRepository;
import ua.com.hdcorp.hd.service.interf.EmployeeService;
import ua.com.hdcorp.hd.service.interf.RoleService;
import ua.com.hdcorp.hd.service.util.EmployeeHelper;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

import static ua.com.hdcorp.hd.exception.NotFoundException.Message.DUPLICATE_EMAIL;
import static ua.com.hdcorp.hd.exception.NotFoundException.Message.ROLE_NOT_FOUND;
import static ua.com.hdcorp.hd.utils.Constants.DUPLICATE_EMAIL_MSG;
import static ua.com.hdcorp.hd.utils.Constants.ROLE_NOT_FOUND_MSG;

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
        Optional<Role> role = roleService.findById(employee.getRoles().get(0).getId());
        role.orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND, ROLE_NOT_FOUND_MSG));

        if(!StringUtils.isEmpty(findByUsername(employee.getEmail()).getEmail())){
            throw new NotFoundException(DUPLICATE_EMAIL, DUPLICATE_EMAIL_MSG);
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRoles(List.of(role.get()));
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
