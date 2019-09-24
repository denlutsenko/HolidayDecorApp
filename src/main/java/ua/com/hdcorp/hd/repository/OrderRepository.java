package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Order;

@Repository
public interface OrderRepository extends RefreshableRepository<Order, Long> {
}
