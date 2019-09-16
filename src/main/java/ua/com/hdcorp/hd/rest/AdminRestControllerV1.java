package ua.com.hdcorp.hd.rest;


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ua.com.hdcorp.hd.dto.AdminEmployeeDTO;
//import ua.com.hdcorp.hd.model.Employee;
//import ua.com.hdcorp.hd.service.EmployeeService;


//@RestController
//@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {
//
//    private final EmployeeService employeeService;
//
//    @Autowired
//    public AdminRestControllerV1(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//
//    @GetMapping(value = "users/{id}")
//    public ResponseEntity<AdminEmployeeDTO> getUserById(@PathVariable(name = "id") Long id) {
//        Employee user = employeeService.findById(id);
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        AdminEmployeeDTO result = AdminEmployeeDTO.fromUser(user);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
