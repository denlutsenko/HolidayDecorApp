package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.InventoryHistory;

@Repository
public interface InventoryHistoryRepository extends RefreshableRepository<InventoryHistory, Long> {
}
