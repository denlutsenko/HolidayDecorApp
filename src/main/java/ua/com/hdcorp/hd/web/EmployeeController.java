package ua.com.hdcorp.hd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.dto.EmployeeDto;
import ua.com.hdcorp.hd.service.interf.EmployeeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "admin/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.registerNewEmployee(employee));
    }

    @GetMapping(value = "employees/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findEmployee(@PathVariable("employeeId") Long employeeId) {
        return ResponseEntity.ok(employeeService.findEmployee(employeeId));
    }

    @GetMapping(value = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> findAllActiveEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PatchMapping(value = "admin/employees/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("employeeId") Long employeeId, @Valid  @RequestBody Employee employeePatch) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employeeId, employeePatch));
    }

    @DeleteMapping(value = "employees/{employeeId}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployee(employeeId));
    }
}
