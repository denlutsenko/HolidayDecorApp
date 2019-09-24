package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Price;

@Repository
public interface PriceRepository extends RefreshableRepository<Price, Long> {
}
