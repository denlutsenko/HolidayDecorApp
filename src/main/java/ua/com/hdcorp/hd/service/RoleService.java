package ua.com.hdcorp.hd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ua.com.hdcorp.hd.exception.BadRequestException;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.RoleRepository;

import javax.validation.Valid;
import java.util.List;

import static ua.com.hdcorp.hd.exception.BadRequestException.Message.ROLE_ALREADY_EXISTS;

@Service
@Validated
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role save(@Valid Role role) {
        if (isRoleExists(role.getName())) {
            throw new BadRequestException(ROLE_ALREADY_EXISTS, "Such Role already exists by ID");
        }
        return roleRepository.save(role);
    }

    public boolean isRoleExists(String roleName) {
        return roleRepository.findByName(roleName).isPresent();
    }

    public boolean isRoleExists(Long id) {
        return roleRepository.findById(id).isPresent();
    }
}