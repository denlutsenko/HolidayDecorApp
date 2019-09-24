package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Inventory;

@Repository
public interface InventoryRepository extends RefreshableRepository<Inventory, Long> {
}
