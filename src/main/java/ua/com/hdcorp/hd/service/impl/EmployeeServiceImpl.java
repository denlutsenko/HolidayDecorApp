package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.repository.EmployeeRepository;
import ua.com.hdcorp.hd.service.interf.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

private final EmployeeRepository employeeRepository;

@Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.getOne(id);
    }
}