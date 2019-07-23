package ua.com.hdcorp.hd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.repository.EmployeeRepository;
import ua.com.hdcorp.hd.util.EntityPatchHelper;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static ua.com.hdcorp.hd.exception.NotFoundException.Message.EMPLOYEE_NOT_FOUND;
import static ua.com.hdcorp.hd.exception.NotFoundException.Message.ROLE_NOT_FOUND;

@Service
@Validated
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final EntityPatchHelper entityPatchHelper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleService roleService, EntityPatchHelper entityPatchHelper) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.entityPatchHelper = entityPatchHelper;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND, "Such Employee is not found by ID"));
    }

    public Employee save(@Valid Employee employee) {
        if (!roleService.isRoleExists(employee.getRole().getId())) {
            throw new NotFoundException(ROLE_NOT_FOUND, "Such Role does not exist by ID");
        }
        Employee savedEmployee = employeeRepository.save(employee);
        employeeRepository.refresh(savedEmployee);
        return savedEmployee;
    }

    public Employee update(Long employeeId, Map<String, String> employeePatch) {
        Employee employee = findById(employeeId);
        entityPatchHelper.patch(employee, employeePatch);
        return save(employee);
    }

    public Employee deactivate(Long employeeId) {
        Employee employee = findById(employeeId);
        employee.setActiveStatus(false);
        return save(employee);
    }
}