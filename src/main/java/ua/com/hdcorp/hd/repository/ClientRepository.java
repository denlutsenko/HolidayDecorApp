package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Client;

@Repository
public interface ClientRepository extends RefreshableRepository<Client, Long> {

}
