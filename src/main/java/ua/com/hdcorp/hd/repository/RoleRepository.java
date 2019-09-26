package ua.com.hdcorp.hd.repository;


import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends RefreshableRepository<Role, Long> {
    Role findByName(String name);
    Optional<Role> findById(Long id);
}
