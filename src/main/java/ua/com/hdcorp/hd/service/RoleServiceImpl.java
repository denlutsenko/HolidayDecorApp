package ua.com.hdcorp.hd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.BadRequest;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.RoleRepository;

import java.util.List;

@Service

public class RoleServiceImpl {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<Role> getRoles() {
//        List<Role> roles = roleRepository.findAll();
//
//        if(!roles.isEmpty()){
//            return roles;
//        }else{
//            throw  new NotFoundException(" 404 custom error");
//        }

        System.out.println(roleRepository.findAll());

        return roleRepository.findAll();
    }


    public Role save(Role role) {
        if (isRoleExists(role.getName())) {
            throw new BadRequest("Role already exists: " + role.getName());
        }
        return roleRepository.save(role);
    }

    private boolean isRoleExists(String roleName) {
        return roleRepository.findAll().stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }
}