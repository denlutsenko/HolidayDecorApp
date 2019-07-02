package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByActiveStatus(boolean flag);

    Client getClientByIdAndActiveStatus(Long id, boolean flag);

    @Transactional
    @Modifying
    @Query(value = "update clients c  set c.active_status = ?1 where c.id = ?2", nativeQuery = true)
    void updateActiveStatus(boolean status, Long id);
}
