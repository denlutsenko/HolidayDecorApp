package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.service.interf.EmployeeService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "admin/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.registerNewEmployee(employee));
    }

    @GetMapping(value = "employees",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> findEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

}
