package ua.com.hdcorp.hd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.model.Status;
import ua.com.hdcorp.hd.repository.EmployeeRepository;
import ua.com.hdcorp.hd.repository.RoleRepository;
import ua.com.hdcorp.hd.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee register(Employee user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        Employee registeredUser = userRepository.save(user);


        return registeredUser;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> result = userRepository.findAll();
        return result;
    }

    @Override
    public Employee findByUsername(String username) {
        Employee result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public Employee findById(Long id) {
        Employee result = userRepository.findById(id).orElse(null);

        if (result == null) {
            return null;
        }

        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
