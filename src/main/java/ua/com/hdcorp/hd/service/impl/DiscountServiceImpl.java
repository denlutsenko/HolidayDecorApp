package ua.com.hdcorp.hd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.hdcorp.hd.model.Discount;
import ua.com.hdcorp.hd.repository.DiscountRepository;
import ua.com.hdcorp.hd.service.interf.IDiscountService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class DiscountServiceImpl implements IDiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(final DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public List<Discount> getDiscountList() {
        return discountRepository.findAll();
    }

    public Discount getOrderDiscount(BigDecimal orderSum) {
        List<Discount> discounts = getDiscountList();
        discounts.sort(Comparator.comparing(Discount::getSumBarrier));

        //TODO Костыль. Сделать нормальную реализацию!!!
        Discount result = discounts.get(0);
        for (int i = 0; i < discounts.size(); i++) {
            if (orderSum.compareTo(discounts.get(i).getSumBarrier()) <= 0) {
                result = discounts.get(i);
                break;
            } else if (i == discounts.size() - 1 && (orderSum.compareTo(discounts.get(i).getSumBarrier()) >= 1)) {
                result = discounts.get(i);
                break;
            }
        }

        return result;
    }
}
