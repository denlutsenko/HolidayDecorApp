package ua.com.hdcorp.hd.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtEmployeeFactory {

    public JwtEmployeeFactory() {
    }

    public static JwtEmployee create(Employee employee) {
        return new JwtEmployee(
                employee.getId(),
                employee.getUsername(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(employee.getRoles())),
                employee.getStatus().equals(Status.ACTIVE),
                employee.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> employeeRole) {
        return employeeRole.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
