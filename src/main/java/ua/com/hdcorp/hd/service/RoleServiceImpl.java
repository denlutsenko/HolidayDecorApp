package ua.com.hdcorp.hd.service;


import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.BadRequest;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.RoleRepository;

import java.util.List;

import static ua.com.hdcorp.hd.exception.BadRequest.Message.ROLE_ALREADY_EXISTS;

@Service

public class RoleServiceImpl {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role save(Role role) {
        if (isRoleExists(role.getName())) {
            throw new BadRequest(ROLE_ALREADY_EXISTS);
        }
        return roleRepository.save(role);
    }

    private boolean isRoleExists(String roleName) {
        return roleRepository.findAll().stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }
}