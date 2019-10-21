package ua.com.hdcorp.hd.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.service.interf.RoleService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "roles/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findRole(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.ok(roleService.findById(roleId));
    }

    @GetMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> findAllRoles() {
        return ResponseEntity.ok(roleService.findAllRoles());
    }
/*
    @DeleteMapping(value = "roles/{roleId}")
    public ResponseEntity<Role> deleteRole(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.deleteRole(roleId));
    }
 */
}
