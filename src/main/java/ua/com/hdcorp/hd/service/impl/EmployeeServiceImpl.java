package ua.com.hdcorp.hd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.DuplicateFoundException;
import ua.com.hdcorp.hd.exception.NoContentException;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.model.Status;
import ua.com.hdcorp.hd.model.dto.EmployeeDto;
import ua.com.hdcorp.hd.repository.EmployeeRepository;
import ua.com.hdcorp.hd.service.helper.EntityPatchHelper;
import ua.com.hdcorp.hd.service.interf.EmployeeService;
import ua.com.hdcorp.hd.service.interf.RoleService;
import ua.com.hdcorp.hd.service.helper.EmployeeHelper;

import java.util.*;

import static ua.com.hdcorp.hd.utils.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmployeeHelper employeeHelper;
    private final EntityPatchHelper entityPatchHelper;

    @Autowired

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder, EmployeeHelper employeeHelper, EntityPatchHelper entityPatchHelper) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.employeeHelper = employeeHelper;
        this.entityPatchHelper = entityPatchHelper;
    }

    @Override
    public EmployeeDto registerNewEmployee(Employee employee) {
        Date date = new Date();
        Role role = roleService.findById(employee.getRole().getId()).orElseThrow(() -> new NoContentException(ROLE_NOT_FOUND));

        if (findByUsername(employee.getEmail()) != null) {
            throw new DuplicateFoundException(DUPLICATE_EMAIL);
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(role);
        employee.setCreated(date);
        employee.setUpdated(date);
        Employee savedEmployee = employeeRepository.save(employee);
        employeeRepository.refresh(savedEmployee);

        return employeeHelper.convertToEmployeeDto(savedEmployee);
    }

    @Override
    public Employee findByUsername(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        employeeRepository.findAllActiveEmployees().forEach((employee) -> {
            employeesDto.add(employeeHelper.convertToEmployeeDto(employee));
        });
        return employeesDto;
    }

    @Override
    public EmployeeDto findEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        return employeeHelper.convertToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, Map<String, String> employeePatch) {
        Employee employee = getEmployeeById(id);
        entityPatchHelper.patch(employee, employeePatch);

        Employee savedEmployee = employeeRepository.save(employee);
        employeeRepository.refresh(savedEmployee);
        return employeeHelper.convertToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setStatus(Status.DELETED);

        Employee savedEmployee = employeeRepository.save(employee);
        employeeRepository.refresh(savedEmployee);
        return employeeHelper.convertToEmployeeDto(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findByIdAndActiveStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new NoContentException(EMPLOYEE_NOT_FOUND));
    }
}
