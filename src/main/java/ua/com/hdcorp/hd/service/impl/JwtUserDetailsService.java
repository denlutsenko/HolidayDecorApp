package ua.com.hdcorp.hd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.securityconfig.jwt.JwtEmployeeFactory;
import ua.com.hdcorp.hd.service.EmployeeService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;

    @Autowired
    public JwtUserDetailsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee user = employeeService.findByUsername(email);

        if (user == null) {
            throw new UsernameNotFoundException("User by email: " + email + " not found");
        }

        return JwtEmployeeFactory.create(user);
    }
}
