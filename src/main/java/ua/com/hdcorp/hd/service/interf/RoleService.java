package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role findByName(final String roleName);

    Role findById(final Long id);

    List<Role> findAllRoles();

    Role deleteRole(final Long roleId);
}
