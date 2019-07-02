package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.hdcorp.hd.model.ClientStore;

@Repository
public interface ClientStoreRepository extends JpaRepository<ClientStore, Long> {

    @Transactional
    @Modifying
    @Query(value = "update client_stores   set active_status = ?1 where id = ?2", nativeQuery = true)
    void updateActiveStatus(boolean flag, Long id);
}

