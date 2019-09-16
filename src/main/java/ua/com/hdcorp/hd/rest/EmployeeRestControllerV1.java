package ua.com.hdcorp.hd.rest;

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
@RequestMapping(value = "/api/v1/users/")
public class EmployeeRestControllerV1 {
//    private final EmployeeService userService;
//
//    @Autowired
//    public EmployeeRestControllerV1(EmployeeService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping(value = "{id}")
//    public ResponseEntity<EmployeeDto> getUserById(@PathVariable(name = "id") Long id){
//        Employee user = userService.findById(id);
//
//        if(user == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        EmployeeDto result = EmployeeDto.fromEmployee(user);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
