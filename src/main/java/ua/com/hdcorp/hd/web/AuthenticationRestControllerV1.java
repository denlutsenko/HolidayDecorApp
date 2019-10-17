package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.configuration.securityconfig.jwt.JwtTokenProvider;
import ua.com.hdcorp.hd.service.interf.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {
    private final static String PASSWORD = "password";
    private final static String EMAIL = "email";
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmployeeService employeeService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, EmployeeService employeeService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.employeeService = employeeService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody Map<String, String> requestDto) {
        try {
            String email = requestDto.get(EMAIL);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.get(PASSWORD)));
            Employee employee = employeeService.findByUsername(email);

            if (employee == null) {
                throw new UsernameNotFoundException("User with username: " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email, employee.getRole());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
