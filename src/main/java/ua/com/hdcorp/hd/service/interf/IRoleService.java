package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
}
