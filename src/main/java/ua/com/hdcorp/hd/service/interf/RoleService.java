package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Role;

import java.util.Optional;

public interface RoleService {
    Role findByName(final String roleName);

    Optional<Role> findById(final Long id);

    boolean isRoleEmpty(final Long id);
}
