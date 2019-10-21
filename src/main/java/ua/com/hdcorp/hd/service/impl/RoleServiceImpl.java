package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.exception.NoContentException;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.model.Status;
import ua.com.hdcorp.hd.repository.RoleRepository;
import ua.com.hdcorp.hd.service.interf.RoleService;

import java.util.List;

import static ua.com.hdcorp.hd.utils.Constants.ROLE_NOT_FOUND;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(final String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public Role findById(final Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new NoContentException(ROLE_NOT_FOUND));
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAllActiveRoles();
    }

    @Override
    public Role deleteRole(Long roleId) {
        Role role = findById(roleId);
        role.setStatus(Status.DELETED);

        Role savedRole = roleRepository.save(role);
        roleRepository.refresh(savedRole);
        return savedRole;
    }
}
