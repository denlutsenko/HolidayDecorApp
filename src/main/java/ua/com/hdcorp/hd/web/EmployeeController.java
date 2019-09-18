package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.service.EmployeeService;


@RestController
@RequestMapping(value = "/api/v1/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "admin/{id}")
    public ResponseEntity<Employee> getUserById(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
