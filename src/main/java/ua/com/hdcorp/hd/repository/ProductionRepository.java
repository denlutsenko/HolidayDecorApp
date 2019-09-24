package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Production;

@Repository
public interface ProductionRepository extends RefreshableRepository<Production, Long> {
}
