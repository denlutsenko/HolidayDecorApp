package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends RefreshableRepository<Role, Long> {

    Optional<Role> findByName(String name);
}