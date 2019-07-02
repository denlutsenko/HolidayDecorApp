package ua.com.hdcorp.hd.service.interf;

import ua.com.hdcorp.hd.model.Discount;

import java.math.BigDecimal;
import java.util.List;

public interface IDiscountService {
    List<Discount> getDiscountList();
    Discount getOrderDiscount(BigDecimal orderSum);
}
