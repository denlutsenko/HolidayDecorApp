package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}