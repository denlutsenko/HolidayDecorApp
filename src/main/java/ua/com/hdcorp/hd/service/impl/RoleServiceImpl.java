package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.RoleRepository;
import ua.com.hdcorp.hd.service.interf.RoleService;

import java.util.Optional;

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
    public Optional<Role> findById(final Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public boolean isRoleEmpty(final Long id) {
      return findById(id).isPresent();
    }
}
