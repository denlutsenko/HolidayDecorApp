package ua.com.hdcorp.hd.securityconfig.jwt;

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
                employee.getEmail(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPassword(),
                mapToGrantedAuthorities(employee.getRole()),
                employee.getStatus().equals(Status.ACTIVE),
                employee.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role employeeRole) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(employeeRole.getName()));
        return list;
    }
}
