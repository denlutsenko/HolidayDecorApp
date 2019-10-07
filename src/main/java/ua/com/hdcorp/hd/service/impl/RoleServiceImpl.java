package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.hdcorp.hd.exception.NoContentException;
import ua.com.hdcorp.hd.exception.NotFoundException;
import ua.com.hdcorp.hd.model.Role;
import ua.com.hdcorp.hd.repository.RoleRepository;
import ua.com.hdcorp.hd.service.interf.RoleService;

import java.util.Optional;

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


}
