package ua.com.hdcorp.hd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.hdcorp.hd.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
