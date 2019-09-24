package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.RoleRepository;
import ua.com.hdcorp.hd.service.interf.RoleService;

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
    public boolean isRoleEmpty(final Role role) {
      return StringUtils.isEmpty(role);
    }
}
