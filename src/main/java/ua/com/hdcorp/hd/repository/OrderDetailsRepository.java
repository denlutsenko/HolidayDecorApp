package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends RefreshableRepository<OrderDetails, Long> {
}
