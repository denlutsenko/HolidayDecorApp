package ua.com.hdcorp.hd.repository;


import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Role;

@Repository
public interface RoleRepository extends RefreshableRepository<Role, Long> {
    Role findByName(String name);
}
