package ua.com.hdcorp.hd.repository;

import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Currency;

@Repository
public interface CurrencyRepository extends RefreshableRepository<Currency, Long> {
}
