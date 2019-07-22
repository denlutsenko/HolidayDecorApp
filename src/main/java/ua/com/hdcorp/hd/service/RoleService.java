package ua.com.hdcorp.hd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.BadRequest;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.RoleRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static ua.com.hdcorp.hd.exception.BadRequest.Message.ROLE_ALREADY_EXISTS;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role save(@Valid Role role) {
        if (isRoleExists(role.getName())) {
            throw new BadRequest(ROLE_ALREADY_EXISTS);
        }
        return roleRepository.save(role);
    }

    public Optional<Role> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public boolean isRoleExists(String roleName) {
        return roleRepository.findAll().stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }

    public boolean isRoleExists(Long id) {
        return findRoleById(id).isPresent();
    }
}