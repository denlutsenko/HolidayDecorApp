package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Role;

public interface RoleService {
    Role findByName(final String roleName);

    boolean isRoleEmpty(final Role role);
}
