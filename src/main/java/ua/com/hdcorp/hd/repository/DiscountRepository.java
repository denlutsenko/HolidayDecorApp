package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Discount;

@Repository
public interface DiscountRepository extends RefreshableRepository<Discount, Long> {
}
