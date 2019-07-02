package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

