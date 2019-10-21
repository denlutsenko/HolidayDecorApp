package ua.com.hdcorp.hd.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Employee;
import ua.com.hdcorp.hd.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends RefreshableRepository<Role, Long> {
    Role findByName(String name);
    Optional<Role> findById(Long id);

    @Query(value = "SELECT * FROM roles WHERE status='ACTIVE'", nativeQuery = true)
    List<Role> findAllActiveRoles();
}
