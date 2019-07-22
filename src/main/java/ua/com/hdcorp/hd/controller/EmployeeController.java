package ua.com.hdcorp.hd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> findEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> findEmployee(@PathVariable("employeeId") Long employeeId) {
        return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
    }

    @PatchMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Map<String, String> employeePatch) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(employeeId, employeePatch));
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<Employee> deactivateEmployee(@PathVariable("employeeId") Long employeeId) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deactivate(employeeId));
    }
}