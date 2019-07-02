package ua.com.hdcorp.hd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Discount;

import java.math.BigDecimal;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findBySumBarrierOrderBySumBarrier(BigDecimal bigDecimal);

}
