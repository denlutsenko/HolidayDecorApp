package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.PriceGroup;

@Repository
public interface PriceGroupRepository extends RefreshableRepository<PriceGroup, Long> {
}
